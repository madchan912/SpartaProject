package com.sparta.ecommerce.user.service;

import com.sparta.ecommerce.user.entity.User;
import com.sparta.ecommerce.user.repository.UserRepository;
import com.sparta.ecommerce.common.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(AESUtil.encrypt(email))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

            return org.springframework.security.core.userdetails.User
                    .withUsername(email)
                    .password(user.getPassword())
                    .authorities("USER")
                    .build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error loading user", e);
        }
    }
}