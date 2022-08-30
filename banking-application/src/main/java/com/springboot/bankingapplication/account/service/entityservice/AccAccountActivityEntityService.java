package com.springboot.bankingapplication.account.service.entityservice;

import com.springboot.bankingapplication.account.dao.AccAccountActivityDao;
import com.springboot.bankingapplication.account.entity.AccAccountActivity;
import com.springboot.bankingapplication.generic.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class AccAccountActivityEntityService extends BaseEntityService<AccAccountActivity, AccAccountActivityDao> {

    public AccAccountActivityEntityService(AccAccountActivityDao dao){
        super(dao);
    }

}
