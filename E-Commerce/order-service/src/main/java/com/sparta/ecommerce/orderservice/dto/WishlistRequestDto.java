package com.sparta.ecommerce.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistRequestDto {
    private Long userId;
    private Long productId;
    private int quantity;
}
