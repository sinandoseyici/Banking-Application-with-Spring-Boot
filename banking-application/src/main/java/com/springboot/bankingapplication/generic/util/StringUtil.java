package com.springboot.bankingapplication.generic.util;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtil {

    public static String getRandomNumberAsString(int charCount){

        if (charCount <=0){
            throw new RuntimeException("Char count can not be a zero or negative number!");
        }

        return RandomStringUtils.randomNumeric(charCount);

    }
}
