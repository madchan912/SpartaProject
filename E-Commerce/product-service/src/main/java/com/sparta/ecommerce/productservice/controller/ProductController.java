package com.sparta.ecommerce.productservice.controller;

import com.sparta.ecommerce.productservice.dto.ProductDetailResponseDto;
import com.sparta.ecommerce.productservice.dto.ProductResponseDto;
import com.sparta.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 전체 상품의 정보
    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    // 특정 상품의 상세 정보
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable Long productId) {
        try {
            ProductDetailResponseDto productDetail = productService.getProductDetail(productId);
            return ResponseEntity.ok(productDetail);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
