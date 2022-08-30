package com.springboot.bankingapplication.customer.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CusCustomerSaveRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Positive
    private Long identityNo;

    @Min(8)
    private String password;

}
