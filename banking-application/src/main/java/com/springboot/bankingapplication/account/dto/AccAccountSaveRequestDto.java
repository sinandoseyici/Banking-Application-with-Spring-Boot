package com.springboot.bankingapplication.account.dto;

import com.springboot.bankingapplication.account.enums.EnumAccAccountType;
import com.springboot.bankingapplication.account.enums.EnumAccCurrencyType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AccAccountSaveRequestDto {

    @NotNull
    @Positive
    private Long cusCustomerId;

    private EnumAccCurrencyType currencyType;
    private EnumAccAccountType accountType;

}
