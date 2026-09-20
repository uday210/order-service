package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // GET /api/orders            -> all
    // GET /api/orders?status=NEW -> filtered
    @GetMapping
    public List<OrderResponse> getAll(@RequestParam(required = false) String status) {
        return status == null ? service.getAll() : service.getByStatus(status);
    }

    // GET /api/orders/1
    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /api/orders
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@Valid @RequestBody OrderRequest request) {
        return service.create(request);
    }

    // PUT /api/orders/1
    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Long id, @Valid @RequestBody OrderRequest request) {
        return service.update(id, request);
    }

    // PATCH /api/orders/1/status?value=SHIPPED
    @PatchMapping("/{id}/status")
    public OrderResponse updateStatus(@PathVariable Long id, @RequestParam("value") String status) {
        return service.updateStatus(id, status);
    }

    // DELETE /api/orders/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}