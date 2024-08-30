package com.sparta.ecommerce.orderservice.repository;

import com.sparta.ecommerce.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUserUserIdAndOrderId(Long userId, Long orderId);
}
