package com.sparta.ecommerce.user.controller;

import com.sparta.ecommerce.user.dto.UserProfileUpdateDto;
import com.sparta.ecommerce.user.dto.UserRegisterRequestDto;
import com.sparta.ecommerce.user.dto.UserRegisterResponseDto;
import com.sparta.ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 사용자 회원 가입
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

    // 특정 대상 정보
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

    // 프로필 업데이트
    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserProfileUpdateDto updateDto, Authentication authentication) {
        try {
            String username = authentication.getName();
            userService.updateProfile(username, updateDto);
            return ResponseEntity.ok("프로필이 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("프로필 업데이트 중 오류가 발생했습니다.");
        }
    }
}
