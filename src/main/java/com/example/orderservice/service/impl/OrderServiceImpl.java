package com.example.orderservice.service.impl;

import com.example.orderservice.service.OrderService;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAll() {
        return repository.findAll().stream().map(OrderResponse::from).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getById(Long id) {
        return OrderResponse.from(find(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getByStatus(String status) {
        return repository.findByStatus(status.toUpperCase())
                .stream().map(OrderResponse::from).toList();
    }

    @Override
    public OrderResponse create(OrderRequest req) {
        Order order = new Order();
        apply(req, order);
        return OrderResponse.from(repository.save(order));
    }

    @Override
    public OrderResponse update(Long id, OrderRequest req) {
        Order order = find(id);
        apply(req, order);
        return OrderResponse.from(repository.save(order));
    }

    @Override
    public OrderResponse updateStatus(Long id, String status) {
        Order order = find(id);
        order.setStatus(status.toUpperCase());
        return OrderResponse.from(repository.save(order));
    }

    @Override
    public void delete(Long id) {
        repository.delete(find(id));
    }

    private Order find(Long id) {
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    private void apply(OrderRequest req, Order order) {
        order.setCustomerName(req.customerName());
        order.setProduct(req.product());
        order.setQuantity(req.quantity());
        order.setPrice(req.price());
    }
}