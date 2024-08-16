package com.sparta.ecommerce.service;

import com.sparta.ecommerce.dto.UserRegisterRequestDto;
import com.sparta.ecommerce.dto.UserRegisterResponseDto;
import com.sparta.ecommerce.entity.User;
import com.sparta.ecommerce.repository.UserRepository;
import com.sparta.ecommerce.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 등록 메소드
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto) throws Exception {
        User user = new User();

        // 사용자 정보를 암호화하여 저장
        user.setName(AESUtil.encrypt(requestDto.getUsrName()));
        user.setPassword(AESUtil.encrypt(requestDto.getUsrPssw()));
        user.setAddress(AESUtil.encrypt(requestDto.getUsrAdr()));
        user.setEmail(AESUtil.encrypt(requestDto.getUsrEmail()));
        user.setPhone(AESUtil.encrypt(requestDto.getUsrPhone()));
        user.setEmailVerified(true); //임시로 true로 처리

        userRepository.save(user);

        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setMessage("회원가입이 완료되었습니다. 이메일 인증을 해주세요.");
        return responseDto;
    }

    // 사용자 정보 조회 메소드 (복호화 포함)
    public UserRegisterRequestDto getUserInfo(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        UserRegisterRequestDto dto = new UserRegisterRequestDto();

        // 저장된 암호화 정보를 복호화하여 반환
        dto.setUsrName(AESUtil.decrypt(user.getName()));
        dto.setUsrAdr(AESUtil.decrypt(user.getAddress()));
        dto.setUsrEmail(AESUtil.decrypt(user.getEmail()));
        dto.setUsrPhone(AESUtil.decrypt(user.getPhone()));

        // 비밀번호는 보통 복호화하지 않고 사용
        return dto;
    }
}
