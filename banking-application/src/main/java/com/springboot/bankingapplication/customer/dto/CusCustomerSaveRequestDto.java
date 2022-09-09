package com.springboot.bankingapplication.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class CusCustomerSaveRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Positive
    private Long identityNo;

    @Size(min = 8)
    private String password;

}
