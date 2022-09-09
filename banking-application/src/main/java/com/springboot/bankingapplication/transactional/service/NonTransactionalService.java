package com.springboot.bankingapplication.transactional.service;

import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import com.springboot.bankingapplication.transactional.util.TransactionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class NonTransactionalService {

    private final CusCustomerEntityService cusCustomerEntityService;
    private TransactionalService transactionalService;
    private  final TransactionalService2 transactionalService2;
    private final NonTransactionalConstantService nonTransactionalConstantService;

    @Autowired
    public void setTransactionalService(@Lazy TransactionalService transactionalService){
        this.transactionalService = transactionalService;
    }

    public void save(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts1");
        cusCustomerEntityService.save(cusCustomer);
        System.out.println("end");

    }

    public void saveN2T(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts4-1");
        cusCustomerEntityService.save(cusCustomer);
        transactionalService.save();
        System.out.println("end");

    }

    public void saveButError(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts7");
        cusCustomerEntityService.save(cusCustomer);

        throwException();
        System.out.println("End");

    }

    private void throwException(){
        throw new RuntimeException("Error");
    }

    public void saveN2M(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts11-1");
        cusCustomerEntityService.save(cusCustomer);
        transactionalService.saveMandatory();
        System.out.println("End");

    }

    public void saveN2S(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts14");
        cusCustomerEntityService.save(cusCustomer);
        transactionalService2.saveSupports();
        System.out.println("End");

    }

    public long doSomething(){

        Date startDate = new Date();

        for (int i = 0; i<99999; i++){
            CusCustomer cusCustomer = nonTransactionalConstantService.findById(1L);
        }

        Date endDate = new Date();
        long difference = endDate.getTime() - startDate.getTime();
        return difference;

    }

    public void saveNon2Never(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts20");
        cusCustomerEntityService.save(cusCustomer);
        nonTransactionalConstantService.saveNever();
        System.out.println("end");

    }
}
