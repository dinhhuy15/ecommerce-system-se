package com.example.fashionshop.modules.order.dto;

import com.example.fashionshop.common.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderStatusResponse {
    private Integer orderId;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
