package com.springboot.bankingapplication.customer.dao;

import com.springboot.bankingapplication.customer.entity.CusCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CusCustomerDao extends JpaRepository<CusCustomer, Long> {

}
