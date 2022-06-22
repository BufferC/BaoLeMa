package com.fc.common;

import com.fc.dto.OrderTaskDto;
import com.fc.entity.Orders;
import com.fc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列管理器：用于处理超时订单的相关操作
 * <p>
 * 1、实现了CommandLineRunner接口，会在项目启动时从数据库中获取所有未完成的订单
 * 并假如到延迟队列中等待处理。
 * <p>
 * 2、同时开启新线程异步处理超时订单，不会阻塞当前线程
 */
@Component
public class DelayQueueManager implements CommandLineRunner {
    @Autowired
    private OrderService orderService;
    // 延迟队列
    private final static DelayQueue<CompleteOrderTask> QUEUE = new DelayQueue<>();

    /**
     * 队列中存放的任务包含了简化封装的订单对象以及当前任务的过期时间
     */
    public static class CompleteOrderTask implements Delayed {
        private final OrderTaskDto orderTask;
        private final long expire;

        public CompleteOrderTask(OrderTaskDto orderTask, long expire) {
            this.orderTask = orderTask;
            this.expire = expire * 60 * 1000 + System.currentTimeMillis();
        }

        // 比较是否过期
        @Override
        public int compareTo(Delayed other) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), other.getDelay(TimeUnit.MILLISECONDS));
        }

        // 获取剩余时间
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expire - System.currentTimeMillis(), unit);
        }

        public OrderTaskDto getOrderTaskDto() {
            return orderTask;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompleteOrderTask)) return false;

            CompleteOrderTask that = (CompleteOrderTask) o;

            return orderTask.equals(that.orderTask);
        }

        @Override
        public int hashCode() {
            return orderTask != null ? orderTask.hashCode() : 0;
        }

        @Override
        public String toString() {
            return orderTask + "-" + expire;
        }
    }

    // 项目启动会执行此方法
    @Override
    public void run(String... args) throws Exception {
        System.out.println("队列管理器开始工作~");

        // 获取数据库中当前还未完成的订单
        List<Orders> list = orderService.findUnfinishedOrders();

        // 遍历后添加到队列中，每个订单设置超时时间为半个小时
        for (Orders order : list) {
            QUEUE.put(new CompleteOrderTask(new OrderTaskDto(order.getId(), order.getStatus()), 30));
        }

        // 开启异步请求
        orderService.asyncChangeOrderStatus();
    }

    public static DelayQueue<CompleteOrderTask> getQUEUE() {
        return QUEUE;
    }

    /**
     * 添加任务到队列头
     *
     * @param orderId      订单编号
     * @param ordersStatus 订单状态
     * @param expire       过期时间【单位：分钟】
     */
    public static void put(Long orderId, Integer ordersStatus, Long expire) {
        CompleteOrderTask task = new CompleteOrderTask(new OrderTaskDto(orderId, ordersStatus), System.currentTimeMillis() + expire * 1000 * 60);

        QUEUE.put(task);
    }

    /**
     * 移除指定id对应的订单任务
     *
     * @param orderId 订单id
     * @return 是否移除成功
     */
    public static boolean remove(Long orderId) {
        // 注意这里通过重写了两个equals来实现通过订单id即可判断是同一个对象，简化操作
        CompleteOrderTask task = new CompleteOrderTask(new OrderTaskDto(orderId, null), 0);
        return QUEUE.remove(task);
    }
}
