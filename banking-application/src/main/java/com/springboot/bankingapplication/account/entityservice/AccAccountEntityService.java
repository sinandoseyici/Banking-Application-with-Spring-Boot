package com.springboot.bankingapplication.account.entityservice;

import com.springboot.bankingapplication.account.dao.AccAccountDao;
import com.springboot.bankingapplication.account.entity.AccAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccAccountEntityService {

    private final AccAccountDao accAccountDao;

    public List<AccAccount> findAll(){
        return accAccountDao.findAll();
    }

    public Optional<AccAccount> findById(Long id){
        return accAccountDao.findById(id);
    }

    public AccAccount save(AccAccount accAccount){
        return accAccountDao.save(accAccount);
    }

    public void delete(Long id){
        accAccountDao.deleteById(id);
    }

    public void delete(AccAccount account){
        accAccountDao.delete(account);
    }

    public boolean isExist(Long id){
        return accAccountDao.existsById(id);
    }
}
