package com.springboot.bankingapplication.account.dao;

import com.springboot.bankingapplication.account.entity.AccAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccAccountDao extends JpaRepository<AccAccount, Long> {

}

