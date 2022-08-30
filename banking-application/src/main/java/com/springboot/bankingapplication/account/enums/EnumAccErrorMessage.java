package com.springboot.bankingapplication.account.enums;

import com.springboot.bankingapplication.generic.exceptions.BaseErrorMessage;

public enum EnumAccErrorMessage implements BaseErrorMessage {

    INSUFFICIENT_BALANCE("Insufficient Balance!"),
    CURRENCCY_TYPES_DID_NOT_MATCH_IT_MUST_BE_THE_SAME("Currency types did not match. It must be the same!")
    ;

    private String message;

    EnumAccErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
