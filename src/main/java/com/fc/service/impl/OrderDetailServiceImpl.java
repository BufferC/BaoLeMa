package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fc.dao.OrderDetailDao;
import com.fc.entity.OrderDetail;
import com.fc.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(OrderDetail::getOrderId, orderId);
        return orderDetailDao.selectList(queryWrapper);
    }

    @Override
    public boolean saveBatch(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            orderDetailDao.insert(orderDetail);
        }
        return true;
    }
}