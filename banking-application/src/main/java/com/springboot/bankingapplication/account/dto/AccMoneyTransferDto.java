package com.springboot.bankingapplication.account.dto;

import com.springboot.bankingapplication.account.enums.EnumAccMoneyTransferType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccMoneyTransferDto {

    private Long id;
    private Long accAccountIdFrom;
    private Long accAccountIdTo;
    private BigDecimal amount;
    private Date transactionDate;
    private String description;
    private EnumAccMoneyTransferType moneyTransferType;

}
