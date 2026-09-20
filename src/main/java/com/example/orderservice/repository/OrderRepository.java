package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(String status);
    List<Order> findByCustomerNameIgnoreCase(String name);
    List<Order> findByStatusAndCustomerName(String status, String name);
    List<Order> findByQuantityGreaterThan(int qty);
    List<Order> findByProductContaining(String text);      // LIKE %text%
    List<Order> findByStatusOrderByCreatedAtDesc(String status);
    long countByStatus(String status);
    boolean existsByCustomerName(String name);
}