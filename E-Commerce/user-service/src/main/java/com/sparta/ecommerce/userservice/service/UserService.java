package com.sparta.ecommerce.user.service;

import com.sparta.ecommerce.user.dto.UserProfileUpdateDto;
import com.sparta.ecommerce.user.dto.UserRegisterRequestDto;
import com.sparta.ecommerce.user.dto.UserRegisterResponseDto;
import com.sparta.ecommerce.user.entity.User;
import com.sparta.ecommerce.user.repository.UserRepository;
import com.sparta.ecommerce.common.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        user.setEmailVerified(false); // 초기 상태는 이메일 인증 미완료

        userRepository.save(user);

        // 이메일 인증 코드 생성 및 전송
        try {
            emailVerificationService.sendVerificationEmail(user.getUserId(), requestDto.getUsrEmail());
        } catch (Exception e) {
            // 예외가 발생하면 로그를 출력하고, 적절한 응답을 설정
            e.printStackTrace();
            UserRegisterResponseDto errorResponse = new UserRegisterResponseDto();
            errorResponse.setMessage("이메일 인증 코드를 전송하는 중에 오류가 발생했습니다.");
            return errorResponse;
        }

        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setMessage("회원가입이 완료되었습니다. 이메일 인증을 해주세요.");
        return responseDto;
    }

    // 사용자 정보 조회 메소드 (복호화 포함)
    public UserRegisterRequestDto getUserInfo(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        UserRegisterRequestDto dto = new UserRegisterRequestDto();
        dto.setUsrName(AESUtil.decrypt(user.getName()));
        dto.setUsrAdr(AESUtil.decrypt(user.getAddress()));
        dto.setUsrEmail(AESUtil.decrypt(user.getEmail()));
        dto.setUsrPhone(AESUtil.decrypt(user.getPhone()));
        // 비밀번호는 보통 복호화하지 않고 사용
        return dto;
    }

    // 비밀번호 검증 메소드
    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    // 프로필 업데이트 메소드
    public void updateProfile(String email, UserProfileUpdateDto updateDto) throws Exception {
        User user = userRepository.findByEmail(AESUtil.encrypt(email))
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (updateDto.getAddress() != null) {
            user.setAddress(AESUtil.encrypt(updateDto.getAddress()));
        }
        if (updateDto.getPhone() != null) {
            user.setPhone(AESUtil.encrypt(updateDto.getPhone()));
        }

        userRepository.save(user);
    }
}