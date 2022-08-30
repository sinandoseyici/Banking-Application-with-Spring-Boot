package com.springboot.bankingapplication.account.validator;

import com.springboot.bankingapplication.account.entity.AccAccount;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccValidator {

    public void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Insufficent Balance!");
        }
    }

    public void validateCurrencyTypes(AccAccount accAccountFrom, AccAccount accAccountTo){
        if (!accAccountFrom.getCurrencyType().equals(accAccountTo.getCurrencyType())){
            throw new RuntimeException("Currency types did not match. It must be the same!");
        }
    }
}
