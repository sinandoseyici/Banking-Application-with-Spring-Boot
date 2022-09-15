package com.springboot.bankingapplication.jwt.controller;

import com.springboot.bankingapplication.customer.dto.CusCustomerDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerSaveRequestDto;
import com.springboot.bankingapplication.generic.response.RestResponse;
import com.springboot.bankingapplication.jwt.dto.JwtLoginRequestDto;
import com.springboot.bankingapplication.jwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtLoginRequestDto jwtLoginRequestDto){

        String token = authenticationService.login(jwtLoginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto = authenticationService.register(cusCustomerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));

    }
}
