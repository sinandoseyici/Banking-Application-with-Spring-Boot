package com.springboot.bankingapplication.account.converter;

import com.springboot.bankingapplication.account.dto.AccAccountSaveRequestDto;
import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class AccACcountMapperAbstract {

    @Autowired
    CusCustomerEntityService cusCustomerEntityService;

    @Mapping(
            target = "cusCustomer",
            expression = "java(cusCustomerEntityService.findByIdWithControl(accAccountSaveRequestDto.getCusCustomerId()))"
    )
    public abstract AccAccount convertToAccAccount(AccAccountSaveRequestDto accAccountSaveRequestDto);
}
