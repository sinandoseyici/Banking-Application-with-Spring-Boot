package com.springboot.bankingapplication.customer.dto;

import lombok.Data;

@Data
public class CusCustomerSaveRequestDto {

    private String name;
    private String surname;
    private Long identityNo;
    private String password;

}
