package com.sparta.ecommerce.service;

import com.sparta.ecommerce.dto.UserRegisterRequestDto;
import com.sparta.ecommerce.dto.UserRegisterResponseDto;
import com.sparta.ecommerce.entity.User;
import com.sparta.ecommerce.repository.UserRepository;
import com.sparta.ecommerce.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 사용자 등록 메소드
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto) throws Exception {
        User user = new User();

        // 비밀번호 해싱
        String hashedPassword = passwordEncoder.encode(requestDto.getUsrPssw());
        user.setPassword(hashedPassword);

        // 개인 정보 암호화
        user.setName(AESUtil.encrypt(requestDto.getUsrName()));
        user.setAddress(AESUtil.encrypt(requestDto.getUsrAdr()));
        user.setEmail(AESUtil.encrypt(requestDto.getUsrEmail()));
        user.setPhone(AESUtil.encrypt(requestDto.getUsrPhone()));
        user.setEmailVerified(true);

        userRepository.save(user);

        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setMessage("회원가입이 완료되었습니다. 이메일 인증을 해주세요.");
        return responseDto;
    }

    // 사용자 정보 조회 메소드
    public UserRegisterRequestDto getUserInfo(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        UserRegisterRequestDto dto = new UserRegisterRequestDto();
        dto.setUsrName(AESUtil.decrypt(user.getName()));
        dto.setUsrAdr(AESUtil.decrypt(user.getAddress()));
        dto.setUsrEmail(AESUtil.decrypt(user.getEmail()));
        dto.setUsrPhone(AESUtil.decrypt(user.getPhone()));

        return dto;
    }
}
