package com.springboot.bankingapplication.account.service.entityservice;

import com.springboot.bankingapplication.account.dao.AccMoneyTransferDao;
import com.springboot.bankingapplication.account.entity.AccMoneyTransfer;
import com.springboot.bankingapplication.generic.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class AccMoneyTransferEntityService extends BaseEntityService<AccMoneyTransfer, AccMoneyTransferDao> {

    public AccMoneyTransferEntityService(AccMoneyTransferDao dao) {
        super(dao);
    }
}
