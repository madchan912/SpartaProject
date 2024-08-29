package com.sparta.ecommerce.controller;

import com.sparta.ecommerce.dto.OrderRequestDto;
import com.sparta.ecommerce.dto.OrderResponseDto;
import com.sparta.ecommerce.entity.Order;
import com.sparta.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 특정 주문 조회
    @GetMapping("/order/{userId}/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long userId, @PathVariable Long orderId) {
        Order order = orderService.getOrderDetailsByUserIdAndOrderId(userId, orderId);
        return ResponseEntity.ok(order);
    }

    // 상품 주문
    @PostMapping("/order")
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto responseDto = orderService.placeOrder(orderRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 주문 취소
    @DeleteMapping("/order/{orderNo}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderNo) {
        orderService.cancelOrder(orderNo);
        return ResponseEntity.ok("주문이 취소되었습니다.");
    }
}
