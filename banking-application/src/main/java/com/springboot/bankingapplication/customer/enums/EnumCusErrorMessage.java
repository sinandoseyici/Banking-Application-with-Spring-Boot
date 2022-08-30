package com.springboot.bankingapplication.customer.enums;

import com.springboot.bankingapplication.generic.exceptions.BaseErrorMessage;

public enum EnumCusErrorMessage implements BaseErrorMessage {

    CUSTOMER_DOES_NOT_EXIST("Customer does not exist!")
    ;

    private String message;

    EnumCusErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){
        return message;
    }
}
