package com.springboot.bankingapplication.account.dto;

import com.springboot.bankingapplication.account.enums.EnumAccMoneyTransferType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class AccMoneyTransferRequestDto {

    @NotNull
    @Positive
    private Long accAccountIdFrom;

    @NotNull
    @Positive
    private Long accAccountIdTo;

    @NotNull
    @Positive
    private BigDecimal amount;
    private String description;
    private EnumAccMoneyTransferType moneyTransferType;

}
