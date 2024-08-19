package com.sparta.ecommerce.controller;

import com.sparta.ecommerce.dto.OrderRequestDto;
import com.sparta.ecommerce.dto.OrderResponseDto;
import com.sparta.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 주문 등록
    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto responseDto = orderService.placeOrder(orderRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 주문 상태 조회
    @GetMapping("/{orderId}/status")
    public ResponseEntity<String> getOrderStatus(@PathVariable Long orderId) {
        String status = orderService.getOrderStatus(orderId);
        return ResponseEntity.ok(status);
    }

    // 주문 취소
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        try {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("주문이 취소되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 상품 반품
    @PutMapping("/{orderId}/return")
    public ResponseEntity<String> returnOrder(@PathVariable Long orderId) {
        try {
            orderService.returnOrder(orderId);
            return ResponseEntity.ok("반품 신청이 완료되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
