package com.sparta.ecommerce.productservice.repository;

import com.sparta.ecommerce.productservice.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
