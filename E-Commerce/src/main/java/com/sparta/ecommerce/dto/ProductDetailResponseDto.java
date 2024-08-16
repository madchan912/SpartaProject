package com.sparta.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailResponseDto {
    private Long productId;      // 상품 아이디
    private String title;        // 상품 제목
    private int price;           // 상품 가격
    private String category;     // 상품 카테고리
    private String detailTitle;  // 상세 제목
    private String detailPicture;// 상세 사진
    private int quantity;        // 수량
}
