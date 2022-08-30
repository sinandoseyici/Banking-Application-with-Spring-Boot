package com.springboot.bankingapplication.account.util;

import com.springboot.bankingapplication.generic.util.StringUtil;

public class AccAccountUtil {

    public static String getIbanNo() {

        String randomNumberAsString = StringUtil.getRandomNumberAsString(24);

        return "TR" + randomNumberAsString;
    }

}
