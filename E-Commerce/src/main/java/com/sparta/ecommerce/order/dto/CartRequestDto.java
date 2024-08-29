package com.sparta.ecommerce.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDto {
    private Long usrId;
    private Long prdId;
    private int quantity;
}
