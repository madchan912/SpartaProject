package com.sparta.ecommerce.userservice.service;

import com.sparta.ecommerce.userservice.entity.EmailVerification;
import com.sparta.ecommerce.userservice.entity.User;
import com.sparta.ecommerce.userservice.repository.UserRepository;
import com.sparta.ecommerce.userservice.repository.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailVerificationRepository emailVerificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    // 이메일 인증 코드를 생성하고 이메일을 전송하는 메서드
    public void sendVerificationEmail(Long userId, String userEmail) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

            String verificationCode = UUID.randomUUID().toString();
            LocalDateTime expiresAt = LocalDateTime.now().plusHours(24);

            EmailVerification emailVerification = new EmailVerification();
            emailVerification.setUserId(userId);
            emailVerification.setVerificationCode(verificationCode);
            emailVerification.setExpiresAt(expiresAt);
            emailVerificationRepository.save(emailVerification);

            // 이메일 전송 로직 추가
            String subject = "이메일 인증 요청";
            String text = "다음 링크를 클릭하여 이메일 인증을 완료하세요: " +
                    "http://localhost:8080/verify?code=" + verificationCode;

            sendEmail(userEmail, subject, text);

            // 전송 성공 로그
            System.out.println("이메일이 성공적으로 전송되었습니다: " + userEmail);
        } catch (Exception e) {
            // 예외가 발생한 경우 로그를 출력
            e.printStackTrace();
            throw new RuntimeException("이메일 전송 중 오류가 발생했습니다.");
        }
    }


    // 실제 이메일을 전송하는 메서드
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    // 인증 코드를 확인하고 사용자 이메일을 인증하는 메서드
    public boolean verifyEmail(String verificationCode) {
        EmailVerification emailVerification = emailVerificationRepository.findByVerificationCode(verificationCode)
                .orElseThrow(() -> new RuntimeException("유효하지 않은 인증 코드입니다."));

        if (emailVerification.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("인증 코드가 만료되었습니다.");
        }

        if (emailVerification.getVerified()) {
            throw new RuntimeException("이메일이 이미 인증되었습니다.");
        }

        // 인증 테이블의 verified 필드 업데이트
        emailVerification.setVerified(true);
        emailVerificationRepository.save(emailVerification);

        // 사용자 테이블의 email_verified 필드 업데이트
        User user = userRepository.findById(emailVerification.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 이메일 인증 전 로그
        System.out.println("이메일 인증 전 사용자 상태: " + user.getEmailVerified());

        user.setEmailVerified(true);
        userRepository.save(user);

        // 이메일 인증 후 로그
        System.out.println("이메일 인증 후 사용자 상태: " + user.getEmailVerified());

        return true;
    }
}
