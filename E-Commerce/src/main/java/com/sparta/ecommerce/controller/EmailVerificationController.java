package com.sparta.ecommerce.controller;

import com.sparta.ecommerce.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailVerificationController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    // 이메일 인증 링크를 통해 인증 코드를 확인하는 메서드
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String code) {
        try {
            boolean isVerified = emailVerificationService.verifyEmail(code);
            return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
