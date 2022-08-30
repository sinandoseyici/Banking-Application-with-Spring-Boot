package com.springboot.bankingapplication.account.dto;

import com.springboot.bankingapplication.account.enums.EnumAccMoneyTransferType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccMoneyTransferRequestDto {

    private Long accAccountIdFrom;
    private Long accAccountIdTo;
    private BigDecimal amount;
    private String description;
    private EnumAccMoneyTransferType moneyTransferType;

}
