package com.springboot.bankingapplication.transactional.service;

import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import com.springboot.bankingapplication.transactional.util.TransactionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class TransactionalService2 {

    private final CusCustomerEntityService cusCustomerEntityService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRN(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts9-2");
        cusCustomerEntityService.save(cusCustomer);
        System.out.println("end");

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRN(int i){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts10-" + i);
        cusCustomerEntityService.save(cusCustomer);

        if (i == 7){
            throw new RuntimeException("Error");
        }

        System.out.println("end ->" + i);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void saveMandatory(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts12-M");
        cusCustomerEntityService.save(cusCustomer);
        System.out.println("End");

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveSupports(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts13-5");
        cusCustomerEntityService.save(cusCustomer);
        System.out.println("End");

    }

    @Transactional(propagation = Propagation.NESTED)
    public void saveNested(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts16-N");
        cusCustomerEntityService.save(cusCustomer);
        System.out.println("end");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(int i){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts18-" + i);
        cusCustomerEntityService.save(cusCustomer);

        if (i == 7){
            throw new RuntimeException("Error");
        }

        System.out.println("end ->" + i);
    }

}
