package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.common.BaseContext;
import com.fc.dao.OrderDao;
import com.fc.dto.OrdersDto;
import com.fc.entity.*;
import com.fc.exception.CustomException;
import com.fc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 用户下单
     */
    @Transactional
    public void submit(Orders orders) {
        //获得当前用户id
        Long userId = BaseContext.getCurrentId();

        //查询当前用户的购物车数据
        List<ShoppingCart> shoppingCarts = shoppingCartService.findByUserId(userId);

        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw new CustomException("购物车为空，不能下单");
        }

        //查询用户数据
        User user = userService.getById(userId);

        //查询地址数据
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBook == null) {
            throw new CustomException("用户地址信息有误，不能下单");
        }

        long orderId = IdWorker.getId();//订单号

        AtomicInteger amount = new AtomicInteger(0);

        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));//总金额
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        //向订单表插入数据，一条数据
        save(orders);

        //向订单明细表插入数据，多条数据
        orderDetailService.saveBatch(orderDetails);

        //清空购物车数据
        shoppingCartService.removeByUserId(userId);
    }

    @Override
    public Page<Orders> findByPage(int page, int pageSize, String number, String beginTime, String endTime) {
        Page<Orders> ordersPage = new Page<>();

        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(number != null, Orders::getNumber, number);
        queryWrapper.gt(beginTime != null, Orders::getOrderTime, beginTime);
        queryWrapper.lt(beginTime != null, Orders::getOrderTime, endTime);
        queryWrapper.orderByDesc(Orders::getOrderTime);

        Page<Orders> pageInfo = orderDao.selectPage(ordersPage, queryWrapper);

        return pageInfo;
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailService.findByOrderId(orderId);
    }

    @Override
    public Page<OrdersDto> getOrderDto(int pageNum, int pageSize, Long userId) {
        Page<Orders> pageInfo = new Page<>(pageNum, pageSize);
        Page<OrdersDto> pageDto = new Page<>(pageNum, pageSize);

        //构造条件查询对象
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        //这里树直接把分页的全部结果查询出来，没有分页条件
        //添加排序条件，根据更新时间降序排列
        queryWrapper.orderByDesc(Orders::getOrderTime);
        queryWrapper.eq(Orders::getUserId, userId);
        orderDao.selectPage(pageInfo, queryWrapper);

        //对OrderDto进行需要的属性赋值
        List<Orders> records = pageInfo.getRecords();
        List<OrdersDto> orderDtoList = records.stream().map((item) -> {
            OrdersDto orderDto = new OrdersDto();
            //此时的orderDto对象里面orderDetails属性还是空 下面准备为它赋值
            Long orderId = item.getId();//获取订单id
            List<OrderDetail> orderDetailList = getOrderDetailsByOrderId(orderId);
            BeanUtils.copyProperties(item, orderDto);
            //对orderDto进行OrderDetails属性的赋值
            orderDto.setOrderDetails(orderDetailList);

            // 计算总菜品量
            int num = 0;
            for (OrderDetail orderDetail : orderDetailList) {
                num += orderDetail.getNumber();
            }
            orderDto.setSumNum(num);

            return orderDto;
        }).collect(Collectors.toList());

        BeanUtils.copyProperties(pageInfo, pageDto, "records");
        pageDto.setRecords(orderDtoList);
        return pageDto;
    }

    @Override
    public boolean addShoppingCartAgain(Long id) {
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(id);

        for (OrderDetail orderDetail : orderDetails) {
            ShoppingCart shoppingCart = new ShoppingCart();

            shoppingCart.setUserId(BaseContext.getCurrentId());

            if (orderDetail.getSetmealId() != null) {
                shoppingCart.setSetmealId(orderDetail.getSetmealId());
            } else {
                shoppingCart.setDishFlavor(orderDetail.getDishFlavor());
                shoppingCart.setDishId(orderDetail.getDishId());
            }

            BeanUtils.copyProperties(orderDetail, shoppingCart, "id");

            return shoppingCartService.save(shoppingCart);
        }
        return false;
    }

    @Override
    public boolean updateById(Orders orders) {
        return orderDao.updateById(orders) > 0;
    }

    @Override
    public boolean save(Orders orders) {
        return orderDao.insert(orders) > 0;
    }
}