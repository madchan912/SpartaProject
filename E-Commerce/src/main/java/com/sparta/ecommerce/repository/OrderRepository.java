package com.sparta.ecommerce.repository;

import com.sparta.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUserUserIdAndOrderId(Long userId, Long orderId);
}
