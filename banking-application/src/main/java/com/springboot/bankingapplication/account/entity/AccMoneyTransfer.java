package com.springboot.bankingapplication.account.entity;

import com.springboot.bankingapplication.account.enums.EnumAccMoneyTransferType;
import com.springboot.bankingapplication.generic.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ACC_MONEY_TRANSFER")
public class AccMoneyTransfer extends BaseEntity {

    @Id
    @SequenceGenerator(name = "AccMoneyTransfer", sequenceName = "ACC_MONEY_TRANSFER_ID_SEQ")
    @GeneratedValue(generator = "AccMoneyTransfer")
    private Long id;

    @Column(name = "ID_ACC_ACCOUNT_FROM")
    private Long accAccountIdFrom;

    @Column(name = "ID_ACC_ACCOUNT_TO")
    private Long accAccountIdTo;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "MONEY_TRANSFER_TYPE", length = 30)
    private EnumAccMoneyTransferType moneyTransferType;

}
