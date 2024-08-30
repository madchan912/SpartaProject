package com.sparta.ecommerce.user.controller;

import com.sparta.ecommerce.user.dto.LoginRequestDto;
import com.sparta.ecommerce.user.dto.LoginResponseDto;
import com.sparta.ecommerce.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    // 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        String tokenWithPrefix = "Bearer " + token;
        return ResponseEntity.ok(new LoginResponseDto(tokenWithPrefix));
    }
}