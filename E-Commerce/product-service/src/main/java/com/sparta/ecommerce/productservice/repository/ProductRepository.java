package com.sparta.ecommerce.productservice.repository;

import com.sparta.ecommerce.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
