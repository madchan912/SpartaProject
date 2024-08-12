package com.sparta.ecommerce.service;

import com.sparta.ecommerce.dto.ProductResponseDto;
import com.sparta.ecommerce.entity.Product;
import com.sparta.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> {
            ProductResponseDto dto = new ProductResponseDto();
            dto.setPrdId(product.getProductId());
            dto.setPrdTitle(product.getTitle());
            dto.setPrdPrice(product.getPrice());
            dto.setPrdCtg(product.getCategory());
            return dto;
        }).collect(Collectors.toList());
    }
}
