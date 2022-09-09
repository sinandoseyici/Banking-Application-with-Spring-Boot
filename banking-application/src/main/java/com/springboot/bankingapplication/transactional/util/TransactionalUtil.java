package com.springboot.bankingapplication.transactional.util;

import com.springboot.bankingapplication.customer.entity.CusCustomer;
import org.springframework.util.StringUtils;

public class TransactionalUtil {

    public static CusCustomer getCustomer(String suffix){

        String name = "test";
        if (StringUtils.hasText(suffix)){
            name = name + "-" + suffix;
        }

        CusCustomer cusCustomer = new CusCustomer();
        cusCustomer.setName(name);
        cusCustomer.setSurname(name);
        cusCustomer.setIdentityNo(10000000000L);
        cusCustomer.setPassword("testtesttest");

        return cusCustomer;
    }

}
