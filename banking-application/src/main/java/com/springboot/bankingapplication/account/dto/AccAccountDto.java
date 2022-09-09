package com.springboot.bankingapplication.account.dto;

import com.springboot.bankingapplication.account.enums.EnumAccAccountType;
import com.springboot.bankingapplication.account.enums.EnumAccCurrencyType;
import com.springboot.bankingapplication.generic.enums.EnumGenStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class AccAccountDto {

    private Long id;
    private Long cusCustomerId;
    private String ibanNo;
    private BigDecimal currentBalance;
    private EnumAccCurrencyType currencyType;
    private EnumAccAccountType accountType;
    private EnumGenStatus status;
    private Date cancelDate;

}
