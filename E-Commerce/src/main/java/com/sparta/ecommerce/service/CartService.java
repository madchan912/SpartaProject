package com.sparta.ecommerce.service;

import com.sparta.ecommerce.dto.CartRequestDto;
import com.sparta.ecommerce.dto.CartResponseDto;
import com.sparta.ecommerce.entity.Cart;
import com.sparta.ecommerce.entity.Product;
import com.sparta.ecommerce.entity.User;
import com.sparta.ecommerce.repository.CartRepository;
import com.sparta.ecommerce.repository.ProductRepository;
import com.sparta.ecommerce.repository.UserRepository;
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
