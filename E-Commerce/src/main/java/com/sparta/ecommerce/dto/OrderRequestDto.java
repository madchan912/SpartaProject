package com.sparta.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Long usrId;
    private List<OrderItemDto> items;

    @Getter
    @Setter
    public static class OrderItemDto {
        private Long prdId;
        private int quantity;
    }
}
