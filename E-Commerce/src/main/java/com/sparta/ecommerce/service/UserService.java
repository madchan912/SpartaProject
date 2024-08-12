package com.sparta.ecommerce.service;

import com.sparta.ecommerce.dto.UserRegisterRequestDto;
import com.sparta.ecommerce.dto.UserRegisterResponseDto;
import com.sparta.ecommerce.entity.User;
import com.sparta.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto) {
        User user = new User();
        user.setName(requestDto.getUsrName());
        user.setPassword(requestDto.getUsrPssw());
        user.setAddress(requestDto.getUsrAdr());
        user.setEmail(requestDto.getUsrEmail());
        user.setPhone(requestDto.getUsrPhone());
        user.setEmailVerified(false);

        userRepository.save(user);

        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setMessage("회원가입이 완료되었습니다. 이메일 인증을 해주세요.");
        return responseDto;
    }
}
