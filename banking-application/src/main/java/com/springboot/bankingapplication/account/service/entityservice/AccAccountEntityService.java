package com.springboot.bankingapplication.account.service.entityservice;

import com.springboot.bankingapplication.account.dao.AccAccountDao;
import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.generic.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccAccountEntityService extends BaseEntityService<AccAccount, AccAccountDao> {

    public AccAccountEntityService(AccAccountDao dao){
        super(dao);
    }

}
