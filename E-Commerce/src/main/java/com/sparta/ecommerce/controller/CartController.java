package com.sparta.ecommerce.controller;

import com.sparta.ecommerce.dto.CartRequestDto;
import com.sparta.ecommerce.dto.CartResponseDto;
import com.sparta.ecommerce.entity.Cart;
import com.sparta.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<CartResponseDto> addToCart(@RequestBody CartRequestDto requestDto) {
        CartResponseDto responseDto = cartService.addToCart(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<Cart>> getCartItems(@PathVariable Long userId) {
        List<Cart> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/cart")
    public ResponseEntity<CartResponseDto> updateCartItem(@RequestBody CartRequestDto requestDto) {
        CartResponseDto responseDto = cartService.updateCartItem(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
