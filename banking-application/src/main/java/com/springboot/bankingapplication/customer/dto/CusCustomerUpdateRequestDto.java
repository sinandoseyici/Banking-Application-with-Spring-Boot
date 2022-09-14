package com.springboot.bankingapplication.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CusCustomerUpdateRequestDto {

    private Long id;
    private String name;
    private String surname;
    private Long identityNo;
    private String password;
}
