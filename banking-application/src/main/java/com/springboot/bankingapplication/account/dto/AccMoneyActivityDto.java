package com.springboot.bankingapplication.account.dto;

import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.account.enums.EnumAccActivityType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccMoneyActivityDto {

    private AccAccount accAccount;
    private BigDecimal amount;
    private EnumAccActivityType activityType;
}
