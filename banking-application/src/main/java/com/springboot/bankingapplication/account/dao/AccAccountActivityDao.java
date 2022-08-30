package com.springboot.bankingapplication.account.dao;

import com.springboot.bankingapplication.account.entity.AccAccountActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccAccountActivityDao extends JpaRepository<AccAccountActivity, Long> {

}
