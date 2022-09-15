package com.springboot.bankingapplication.jwt.security;

import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import com.springboot.bankingapplication.jwt.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CusCustomerEntityService cusCustomerEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Long identityNo = Long.valueOf(username);

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdentityNo(identityNo);

        return JwtUserDetails.create(cusCustomer);
    }

    public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdWithControl(userId);

        return JwtUserDetails.create(cusCustomer);
    }
}
