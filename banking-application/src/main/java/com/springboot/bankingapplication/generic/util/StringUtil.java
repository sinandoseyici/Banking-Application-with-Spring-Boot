package com.springboot.bankingapplication.generic.util;

import com.springboot.bankingapplication.generic.enums.ErrorMessage;
import com.springboot.bankingapplication.generic.exceptions.BusinessException;
import org.apache.commons.lang3.RandomStringUtils;

public class StringUtil {

    public static String getRandomNumberAsString(int charCount){

        if (charCount <=0){
            throw new BusinessException(ErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE);
        }

        return RandomStringUtils.randomNumeric(charCount);

    }
}
