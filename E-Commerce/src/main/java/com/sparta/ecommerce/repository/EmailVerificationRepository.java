package com.sparta.ecommerce.repository;

import com.sparta.ecommerce.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByVerificationCode(String verificationCode);
}
