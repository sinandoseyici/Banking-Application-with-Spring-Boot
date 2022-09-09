package com.springboot.bankingapplication.transactional.service;

import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import com.springboot.bankingapplication.transactional.util.TransactionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NonTransactionalConstantService {

    private final CusCustomerEntityService cusCustomerEntityService;
    private Map<Long, CusCustomer> idAndCustomerMap = new LinkedHashMap<>();

    public CusCustomer findById(Long id){
        CusCustomer cusCustomer = idAndCustomerMap.get(id);

        if (cusCustomer != null){
            return cusCustomer;
        }

        cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        idAndCustomerMap.put(id, cusCustomer);

        return cusCustomer;
    }

    @Transactional(propagation = Propagation.NEVER)
    public void saveNever(){

        CusCustomer cusCustomer = TransactionalUtil.getCustomer("ts28");
        cusCustomerEntityService.save(cusCustomer);

        System.out.println("end");
    }

}
