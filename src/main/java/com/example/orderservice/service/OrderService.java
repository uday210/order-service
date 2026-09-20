package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAll();

    OrderResponse getById(Long id);

    List<OrderResponse> getByStatus(String status);

    OrderResponse create(OrderRequest request);

    OrderResponse update(Long id, OrderRequest request);

    OrderResponse updateStatus(Long id, String status);

    void delete(Long id);
}