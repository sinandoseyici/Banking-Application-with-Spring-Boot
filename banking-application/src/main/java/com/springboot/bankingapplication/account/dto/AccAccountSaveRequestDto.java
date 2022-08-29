package com.springboot.bankingapplication.account.dto;

import com.springboot.bankingapplication.account.enums.EnumAccAccountType;
import com.springboot.bankingapplication.account.enums.EnumAccCurrencyType;
import lombok.Data;

@Data
public class AccAccountSaveRequestDto {

    private Long cusCustomerId;
    private EnumAccCurrencyType currencyType;
    private EnumAccAccountType accountType;

}
