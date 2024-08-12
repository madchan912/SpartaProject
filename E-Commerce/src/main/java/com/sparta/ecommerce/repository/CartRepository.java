package com.sparta.ecommerce.repository;

import com.sparta.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 특정 사용자에 대한 장바구니 항목을 찾는 메소드
    List<Cart> findByUserUserId(Long userId);

    // 특정 사용자와 상품에 대한 장바구니 항목을 찾는 메소드
    Optional<Cart> findByUserUserIdAndProductProductId(Long userId, Long productId);
}
