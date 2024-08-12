package com.sparta.ecommerce.repository;

import com.sparta.ecommerce.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
