package com.sparta.ecommerce.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistResponseDto {
    private Long wishlistId;
    private Long userId;
    private Long productId;
    private String productTitle;
    private int productPrice;
    private int quantity;
}
