package com.sparta.ecommerce.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long prdId;
    private String prdTitle;
    private int prdPrice;
    private String prdCtg;
}
