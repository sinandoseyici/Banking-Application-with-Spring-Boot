package com.springboot.bankingapplication.jwt.service;

import com.springboot.bankingapplication.customer.dto.CusCustomerDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerSaveRequestDto;
import com.springboot.bankingapplication.customer.service.CusCustomerService;
import com.springboot.bankingapplication.jwt.dto.JwtLoginRequestDto;
import com.springboot.bankingapplication.jwt.enums.JwtConstant;
import com.springboot.bankingapplication.jwt.security.JwtTokenGenerator;
import com.springboot.bankingapplication.jwt.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CusCustomerService cusCustomerService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public CusCustomerDto register(CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        return cusCustomerDto;
    }

    public String login(JwtLoginRequestDto jwtLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtLoginRequestDto.getIdentityNo().toString(),
                jwtLoginRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJWTToken(authentication);

        String bearerToken = JwtConstant.BEARER.getConstant() + token;

        return bearerToken;
    }

    public Long getCurrentCustomerId(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = getJwtUserDetails(authentication);

        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }

        return jwtUserDetailsId;
    }

    private JwtUserDetails getJwtUserDetails(Authentication authentication) {
        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
