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

    // 获取所有未付款和派送后的订单
    List<Orders> findUnfinishedOrders();

    // 用于清理30未付款以及派送后的订单状态修改
    void asyncChangeOrderStatus() throws InterruptedException;
}
