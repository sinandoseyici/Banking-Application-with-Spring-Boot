package com.springboot.bankingapplication.customer.entity.entityservice;

import com.springboot.bankingapplication.customer.dao.CusCustomerDao;
import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.generic.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CusCustomerEntityService extends BaseEntityService<CusCustomer, CusCustomerDao> {

    public CusCustomerEntityService(CusCustomerDao dao) {
        super(dao);
    }

}
