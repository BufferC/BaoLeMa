package com.fc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dto.OrdersDto;
import com.fc.entity.OrderDetail;
import com.fc.entity.Orders;

import java.util.List;


public interface OrderService {
    /**
     * 用户下单
     */
    void submit(Orders orders);

    Page<Orders> findByPage(int page, int pageSize, String number, String beginTime, String endTime);

    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);

    Page<OrdersDto> getOrderDto(int pageNum, int pageSize, Long userId);

    boolean addShoppingCartAgain(Long id);

    boolean updateById(Orders orders);

    boolean save(Orders orders);
}
