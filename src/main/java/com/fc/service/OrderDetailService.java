package com.fc.service;

import com.fc.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findByOrderId(Long orderId);

    boolean saveBatch(List<OrderDetail> orderDetails);
}
