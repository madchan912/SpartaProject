package com.sparta.ecommerce.controller;

import com.sparta.ecommerce.dto.UserRegisterRequestDto;
import com.sparta.ecommerce.dto.UserRegisterResponseDto;
import com.sparta.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto requestDto) {
        try {
            UserRegisterResponseDto responseDto = userService.registerUser(requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            // 예외 처리 (예: 로그 기록, 사용자에게 에러 메시지 반환 등)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserRegisterRequestDto> getUser(@PathVariable Long userId) {
        try {
            UserRegisterRequestDto responseDto = userService.getUserInfo(userId);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            // 예외 처리 (예: 로그 기록, 사용자에게 에러 메시지 반환 등)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
