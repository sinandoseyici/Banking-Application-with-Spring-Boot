package com.springboot.bankingapplication.transactional.service;

import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import com.springboot.bankingapplication.transactional.util.TransactionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionalService {

    private final CusCustomerEntityService cusCustomerEntityService;

    private  final NonTransactionalService nonTransactionalService;
    private final TransactionalService2 transactionalService2;
    private final TransactionalConstantService transactionalConstantService;
    private final NonTransactionalConstantService nonTransactionalConstantService;

    public void save(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts2");

        cusCustomerEntityService.save(cusCustomer);

        System.out.println("end");

    }

    public void saveT2N(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts3-1");

        cusCustomerEntityService.save(cusCustomer);

        System.out.println("end");
    }

    public void saveT2T(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts5-1");

        cusCustomerEntityService.save(cusCustomer);
        save();
        System.out.println("end");
    }

    public void saveButError(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts6-1");
        cusCustomerEntityService.save(cusCustomer);
        throwException();

        System.out.println("End");
    }

    private void throwException(){
        throw new RuntimeException("Error");
    }

    public void saveT2RN(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts8-1");
        cusCustomerEntityService.save(cusCustomer);
        saveRN();
        System.out.println("end");

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveRN(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts8-2");
        cusCustomerEntityService.save(cusCustomer);
        System.out.println("end");

    }

    public void saveT2RNWithDifferentBean(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts9-1");
        cusCustomerEntityService.save(cusCustomer);

        transactionalService2.saveRN();
        System.out.println("end");

    }

    public void saveT2RNButError(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts10");
        cusCustomerEntityService.save(cusCustomer);

        for (int i = 0; i<10; i++){

            try {
                transactionalService2.saveRN(i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("end");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void saveMandatory(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts11-M");
        cusCustomerEntityService.save(cusCustomer);

        System.out.println("end");
    }

    public void saveT2M(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts12");
        cusCustomerEntityService.save(cusCustomer);
        transactionalService2.saveMandatory();
        System.out.println("End");

    }

    public void saveT2S(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts13");
        cusCustomerEntityService.save(cusCustomer);

        transactionalService2.saveSupports();
        System.out.println("End");

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long doSomething() {

        Date startDate = new Date();

        for (int i = 0; i < 99999; i++){
            CusCustomer cusCustomer = transactionalConstantService.findById(1L);
        }

        Date endDate = new Date();
        long difference = endDate.getTime() - startDate.getTime();
        return difference;
    }

    public void saveNested() {

        transactionalService2.saveNested();

        System.out.println("End");
    }

    public void saveT2TButError() {

        CusCustomer customer = TransactionalUtil.getCustomer("ts18");
        cusCustomerEntityService.save(customer);

        for (int i = 0; i < 10; i++){
            try {
                transactionalService2.save(i);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("End");
    }

    public long doSomethingWithNewTransaction() {

        Date startDate = new Date();

        for (int i = 0; i < 9999; i++){
            CusCustomer cusCustomer = transactionalConstantService.findByIdRN(1L);
        }

        Date endDate = new Date();
        long difference = endDate.getTime() - startDate.getTime();
        return difference;
    }

    public void saveT2Never() {

        CusCustomer customer = TransactionalUtil.getCustomer("ts21");
        cusCustomerEntityService.save(customer);

        nonTransactionalConstantService.saveNever();

        System.out.println("end");
    }
}
