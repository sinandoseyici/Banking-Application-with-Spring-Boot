package com.springboot.bankingapplication.transactional.service;

import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;


@Service
@Transactional
@RequiredArgsConstructor
public class TransactionalConstantService {

    private final CusCustomerEntityService cusCustomerEntityService;

    private Map<Long, CusCustomer> idAndCustomerMap = new LinkedHashMap<>();

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public CusCustomer findById(Long id){

        CusCustomer cusCustomer = idAndCustomerMap.get(id);

        if (cusCustomer != null){
            return cusCustomer;
        }

        cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        idAndCustomerMap.put(id, cusCustomer);

        return cusCustomer;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CusCustomer findByIdRN(Long id){

        CusCustomer cusCustomer = idAndCustomerMap.get(id);

        if (cusCustomer != null){
            return cusCustomer;
        }

        cusCustomer = cusCustomerEntityService.findByIdWithControl(id);

        idAndCustomerMap.put(id, cusCustomer);

        return cusCustomer;
    }


}
