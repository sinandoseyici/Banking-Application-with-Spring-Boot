package com.springboot.bankingapplication.customer.entityservice;

import com.springboot.bankingapplication.customer.dao.CusCustomerDao;
import com.springboot.bankingapplication.customer.entity.CusCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CusCustomerEntityService {

    private final CusCustomerDao cusCustomerDao;

    public List<CusCustomer> findAll(){
        return cusCustomerDao.findAll();
    }

    public Optional<CusCustomer> findById(Long id){
        return cusCustomerDao.findById(id);
    }

    public CusCustomer save(CusCustomer cusCustomer){
        return cusCustomerDao.save(cusCustomer);
    }

    public void delete(Long id){
        cusCustomerDao.deleteById(id);
    }

    public void delete(CusCustomer cusCustomer){
        cusCustomerDao.delete(cusCustomer);
    }

    public boolean isExist(Long id){
        return cusCustomerDao.existsById(id);
    }

}
