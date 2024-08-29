package com.sparta.ecommerce.product.repository;

import com.sparta.ecommerce.product.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
