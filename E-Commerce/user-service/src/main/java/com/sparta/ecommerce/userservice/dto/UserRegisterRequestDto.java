package com.sparta.ecommerce.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {
    private String usrName;
    private String usrPssw;
    private String usrAdr;
    private String usrEmail;
    private String usrPhone;
}
