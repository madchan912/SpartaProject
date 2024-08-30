package com.sparta.ecommerce.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileUpdateDto {
    private String address;
    private String phone;
}