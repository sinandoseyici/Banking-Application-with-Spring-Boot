package com.springboot.bankingapplication.generic.enums;

import com.springboot.bankingapplication.generic.exceptions.BaseErrorMessage;

public enum ErrorMessage implements BaseErrorMessage {
    CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE("Char count can not be zero or negative!"),
    ITEM_NOT_FOUND("Item not found!"),

    PARAMETER_CANNOT_BE_NULL("Parameter can not be null!")
    ;

    private String message;

    ErrorMessage(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        return message;
    }
}
