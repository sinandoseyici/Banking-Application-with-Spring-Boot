package com.springboot.bankingapplication.jwt.dto;

import lombok.Data;

@Data
public class JwtLoginRequestDto {

    private Long identityNo;
    private String password;
}
