package com.springboot.bankingapplication.account.entity;

import com.springboot.bankingapplication.account.enums.EnumAccAccountType;
import com.springboot.bankingapplication.account.enums.EnumAccCurrencyType;
import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.generic.EnumGenStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ACC_ACCOUNT")
public class AccAccount {

    @Id
    @SequenceGenerator(name = "AccAccount", sequenceName = "ACC_ACCOUNT_ID_SEQ")
    @GeneratedValue(generator = "AccAccount")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUS_CUSTOMER", foreignKey = @ForeignKey(name = "FK_ACC_ACCOUNT_CUS_CUSTOMER"))
    private CusCustomer cusCustomer;

    @Size(min = 26, max = 26)
    @Column(name = "IBAN_NO", length = 26)
    private String ibanNo;

    @Column(name = "CURRENT_BALANCE", precision = 19, scale = 2)
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY_TYPE", length = 30)
    private EnumAccCurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE", length = 30)
    private EnumAccAccountType accAccountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 30)
    private EnumGenStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CANCEL_DATE")
    private Date cancelDate;
}
