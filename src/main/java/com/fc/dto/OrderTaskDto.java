package com.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装了定时任务中的订单对象，只需要订单的id和状态即可
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTaskDto {
    private Long id;
    private Integer status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderTaskDto)) return false;

        OrderTaskDto that = (OrderTaskDto) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
