package com.sparta.ecommerce.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long orderId;
    private String message;

    public OrderResponseDto(Long orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }
}
