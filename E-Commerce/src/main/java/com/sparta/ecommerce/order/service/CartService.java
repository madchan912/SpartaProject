package com.sparta.ecommerce.order.service;

import com.sparta.ecommerce.order.dto.CartRequestDto;
import com.sparta.ecommerce.order.dto.CartResponseDto;
import com.sparta.ecommerce.order.repository.CartRepository;
import com.sparta.ecommerce.order.entity.Cart;
import com.sparta.ecommerce.product.entity.Product;
import com.sparta.ecommerce.user.entity.User;
import com.sparta.ecommerce.product.repository.ProductRepository;
import com.sparta.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // 사용자 장바구니에 저장
    public CartResponseDto addToCart(CartRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUsrId()).orElseThrow();
        Product product = productRepository.findById(requestDto.getPrdId()).orElseThrow();

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(requestDto.getQuantity());
        cart.setPrice(product.getPrice() * requestDto.getQuantity());

        cartRepository.save(cart);

        CartResponseDto responseDto = new CartResponseDto();
        responseDto.setMessage("장바구니에 추가되었습니다.");
        return responseDto;
    }

    public List<Cart> getCartItems(Long userId) {
        return cartRepository.findByUserUserId(userId);
    }

    // 사용자 장바구니 업데이트
    public CartResponseDto updateCartItem(CartRequestDto requestDto) {
        Cart cart = cartRepository.findByUserUserIdAndProductProductId(requestDto.getUsrId(), requestDto.getPrdId())
                .orElseThrow();

        cart.setQuantity(requestDto.getQuantity());
        cart.setPrice(cart.getProduct().getPrice() * requestDto.getQuantity());

        cartRepository.save(cart);

        CartResponseDto responseDto = new CartResponseDto();
        responseDto.setMessage("장바구니 항목이 수정되었습니다.");
        return responseDto;
    }
}
