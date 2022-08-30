package com.springboot.bankingapplication.account.entity;

import com.springboot.bankingapplication.account.enums.EnumAccActivityType;
import com.springboot.bankingapplication.generic.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ACC_ACCOUNT_ACTIVITY")
public class AccAccountActivity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "AccAccountActivity", sequenceName = "ACC_ACCOUNT_ACTIVITY_ID_SEQ")
    @GeneratedValue(generator = "AccAccountActivity")
    private Long id;

    @Column(name = "ID_ACC_ACCOUNT")
    private Long accAccountId;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "CURRENT_BALANCE", precision = 19, scale = 2)
    private BigDecimal currentBalance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTIVITY_TYPE", length = 30)
    private EnumAccActivityType accActivityType;

}
