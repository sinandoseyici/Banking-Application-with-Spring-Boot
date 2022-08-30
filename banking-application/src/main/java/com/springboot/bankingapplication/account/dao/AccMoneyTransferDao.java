package com.springboot.bankingapplication.account.dao;

import com.springboot.bankingapplication.account.entity.AccMoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccMoneyTransferDao extends JpaRepository<AccMoneyTransfer, Long> {

}
