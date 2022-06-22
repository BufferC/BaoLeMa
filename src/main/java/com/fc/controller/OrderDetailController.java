package com.fc.controller;


import com.fc.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单明细
 */
@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
}