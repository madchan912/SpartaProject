package com.sparta.ecommerce.controller;

import com.sparta.ecommerce.dto.UserRegisterRequestDto;
import com.sparta.ecommerce.dto.UserRegisterResponseDto;
import com.sparta.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto requestDto) {
        UserRegisterResponseDto responseDto = userService.registerUser(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
