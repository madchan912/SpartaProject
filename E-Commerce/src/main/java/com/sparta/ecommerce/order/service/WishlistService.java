package com.sparta.ecommerce.order.service;

import com.sparta.ecommerce.order.dto.WishlistRequestDto;
import com.sparta.ecommerce.order.dto.WishlistResponseDto;
import com.sparta.ecommerce.product.entity.Product;
import com.sparta.ecommerce.user.entity.User;
import com.sparta.ecommerce.order.entity.Wishlist;
import com.sparta.ecommerce.product.repository.ProductRepository;
import com.sparta.ecommerce.order.repository.WishlistRepository;
import com.sparta.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // 위시리스트에 상품 추가
    public WishlistResponseDto addToWishlist(WishlistRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(requestDto.getProductId()).orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlist.setQuantity(requestDto.getQuantity());

        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        WishlistResponseDto responseDto = new WishlistResponseDto();
        responseDto.setWishlistId(savedWishlist.getWishlistId());
        responseDto.setUserId(user.getUserId());
        responseDto.setProductId(product.getProductId());
        responseDto.setProductTitle(product.getTitle());
        responseDto.setProductPrice(product.getPrice());
        responseDto.setQuantity(savedWishlist.getQuantity());

        return responseDto;
    }

    // 특정 사용자의 위시리스트 조회
    public List<WishlistResponseDto> getWishlistByUserId(Long userId) {
        List<Wishlist> wishlistItems = wishlistRepository.findByUserUserId(userId);

        return wishlistItems.stream().map(wishlist -> {
            WishlistResponseDto dto = new WishlistResponseDto();
            dto.setWishlistId(wishlist.getWishlistId());
            dto.setUserId(wishlist.getUser().getUserId());
            dto.setProductId(wishlist.getProduct().getProductId());
            dto.setProductTitle(wishlist.getProduct().getTitle());
            dto.setProductPrice(wishlist.getProduct().getPrice());
            dto.setQuantity(wishlist.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    }

    // 위시리스트 항목 수정 (수량 변경)
    public WishlistResponseDto updateWishlistItem(Long wishlistId, int quantity) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("위시리스트 항목을 찾을 수 없습니다."));
        wishlist.setQuantity(quantity);
        Wishlist updatedWishlist = wishlistRepository.save(wishlist);

        WishlistResponseDto responseDto = new WishlistResponseDto();
        responseDto.setWishlistId(updatedWishlist.getWishlistId());
        responseDto.setUserId(updatedWishlist.getUser().getUserId());
        responseDto.setProductId(updatedWishlist.getProduct().getProductId());
        responseDto.setProductTitle(updatedWishlist.getProduct().getTitle());
        responseDto.setProductPrice(updatedWishlist.getProduct().getPrice());
        responseDto.setQuantity(updatedWishlist.getQuantity());

        return responseDto;
    }

    // 위시리스트 항목 삭제
    public void deleteWishlistItem(Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId).orElseThrow(() -> new RuntimeException("위시리스트 항목을 찾을 수 없습니다."));
        wishlistRepository.delete(wishlist);
    }
}
