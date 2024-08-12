package com.sparta.ecommerce.controller;

import com.sparta.ecommerce.dto.ProductResponseDto;
import com.sparta.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }
}
