package com.springboot.bankingapplication.generic.util;

import com.springboot.bankingapplication.generic.enums.ErrorMessage;
import com.springboot.bankingapplication.generic.exceptions.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void shouldGetRandomNumberAsString() {
        int charCount = 5;
        String string = StringUtil.getRandomNumberAsString(charCount);
        assertEquals(charCount, string.length());
    }

    @Test
    void shouldGetRandomNumberAsStringAllCharsAreNumeric(){
        int charCount = 5;
        String string = StringUtil.getRandomNumberAsString(charCount);
        boolean hasChar = false;

        for (char eachChar : string.toCharArray()){

            boolean isDigit = Character.isDigit(eachChar);
            if (!isDigit){
                hasChar = true;
                break;
            }

            assertFalse(hasChar);
        }
    }

    @Test
    void shouldNotGetRandomNumberAsStringWhenCharCountIsZero(){

        BusinessException businessException = assertThrows(BusinessException.class, () -> StringUtil.getRandomNumberAsString(0));

        assertEquals(ErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE, businessException.getBaseErrorMessage());
    }

    @Test
    void shouldNotGetRandomNumberAsStringWhenCharCountIsNegative(){

        BusinessException businessException = assertThrows(BusinessException.class, () -> StringUtil.getRandomNumberAsString(-1));

        assertEquals(ErrorMessage.CHAR_COUNT_CANNOT_BE_ZERO_OR_NEGATIVE, businessException.getBaseErrorMessage());
    }
}
