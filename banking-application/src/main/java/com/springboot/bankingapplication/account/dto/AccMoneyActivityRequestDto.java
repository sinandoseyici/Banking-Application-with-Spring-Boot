package com.springboot.bankingapplication.account.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class AccMoneyActivityRequestDto {

    @NotNull
    @Positive
    private Long accAccountId;

    @NotNull
    @Positive
    private BigDecimal amount;
}
