package com.fc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dto.OrdersDto;
import com.fc.entity.Orders;
import com.fc.service.OrderService;
import com.fc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     */
    @PostMapping("submit")
    public ResultVO<String> submit(@RequestBody Orders orders) {
        log.info("订单数据：{}", orders);
        orderService.submit(orders);
        return ResultVO.success("下单成功");
    }

    /**
     * 套餐分页查询
     */
    @GetMapping("page")
    public ResultVO<Page<Orders>> page(int page,
                                       int pageSize,
                                       String number,
                                       String beginTime,
                                       String endTime) {
        Page<Orders> pageInfo = orderService.findByPage(page, pageSize, number, beginTime, endTime);
        return ResultVO.success(pageInfo);
    }

    /**
     * 获取用户的所有订单
     */
    @GetMapping("userPage")
    public ResultVO<Page<OrdersDto>> getUserPage(@RequestParam("page") int pageNum,
                                                 @RequestParam("pageSize") int pageSize,
                                                 HttpSession session) {
        Long userId = (Long) session.getAttribute("user");

        Page<OrdersDto> orderDto = orderService.getOrderDto(pageNum, pageSize, userId);

        return ResultVO.success(orderDto);
    }

    /**
     * 更改订单状态
     */
    @PutMapping
    public ResultVO<Orders> updateStatus(@RequestBody Orders orders) {
        if (orderService.updateById(orders)) {
            return ResultVO.success(orders);
        }
        return ResultVO.error("更新失败");
    }

    /**
     * 再来一单
     */
    @PostMapping("again")
    public ResultVO<String> addShoppingCartAgain(@RequestBody Orders orders){
        if (orderService.addShoppingCartAgain(orders.getId())){
            return ResultVO.success("成功！");
        }
        return ResultVO.error("失败!");
    }
}