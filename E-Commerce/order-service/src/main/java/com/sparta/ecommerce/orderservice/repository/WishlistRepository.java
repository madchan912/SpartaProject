package com.sparta.ecommerce.orderservice.repository;

import com.sparta.ecommerce.orderservice.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUserUserId(Long userId);
    Wishlist findByUserUserIdAndProductProductId(Long userId, Long productId);
}
