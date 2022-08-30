package com.springboot.bankingapplication.account.validator;

import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.account.enums.EnumAccErrorMessage;
import com.springboot.bankingapplication.generic.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccValidator {

    public void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new BusinessException(EnumAccErrorMessage.INSUFFICIENT_BALANCE);
        }
    }

    public void validateCurrencyTypes(AccAccount accAccountFrom, AccAccount accAccountTo){
        if (!accAccountFrom.getCurrencyType().equals(accAccountTo.getCurrencyType())){
            throw new BusinessException(EnumAccErrorMessage.CURRENCCY_TYPES_DID_NOT_MATCH_IT_MUST_BE_THE_SAME);
        }
    }
}
