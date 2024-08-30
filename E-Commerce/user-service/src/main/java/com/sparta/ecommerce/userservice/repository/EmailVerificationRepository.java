package com.sparta.ecommerce.userservice.repository;

import com.sparta.ecommerce.userservice.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByVerificationCode(String verificationCode);
}
