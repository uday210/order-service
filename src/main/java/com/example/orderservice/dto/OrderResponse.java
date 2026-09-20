package com.example.orderservice.dto;

import com.example.orderservice.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        String customerName,
        String product,
        Integer quantity,
        BigDecimal price,
        BigDecimal total,
        String status,
        LocalDateTime createdAt
) {
    public static OrderResponse from(Order o) {
        return new OrderResponse(
                o.getId(),
                o.getCustomerName(),
                o.getProduct(),
                o.getQuantity(),
                o.getPrice(),
                o.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())),
                o.getStatus(),
                o.getCreatedAt()
        );
    }
}