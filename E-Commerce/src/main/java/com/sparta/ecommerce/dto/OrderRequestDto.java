package com.sparta.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long usrId;
    private Long prdId;
    private int quantity;
}
