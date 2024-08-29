package com.sparta.ecommerce.controller;

import com.sparta.ecommerce.dto.WishlistRequestDto;
import com.sparta.ecommerce.dto.WishlistResponseDto;
import com.sparta.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // 위시리스트에 상품 추가
    @PostMapping
    public ResponseEntity<WishlistResponseDto> addToWishlist(@RequestBody WishlistRequestDto requestDto) {
        WishlistResponseDto responseDto = wishlistService.addToWishlist(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 특정 사용자의 위시리스트 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistResponseDto>> getWishlistByUserId(@PathVariable Long userId) {
        List<WishlistResponseDto> wishlistItems = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlistItems);
    }

    // 위시리스트 항목 수정 (수량 변경)
    @PutMapping("/{wishlistId}")
    public ResponseEntity<WishlistResponseDto> updateWishlistItem(@PathVariable Long wishlistId, @RequestParam int quantity) {
        WishlistResponseDto responseDto = wishlistService.updateWishlistItem(wishlistId, quantity);
        return ResponseEntity.ok(responseDto);
    }

    // 위시리스트 항목 삭제
    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable Long wishlistId) {
        wishlistService.deleteWishlistItem(wishlistId);
        return ResponseEntity.noContent().build(); // 상태 코드 204 반환
    }
}
