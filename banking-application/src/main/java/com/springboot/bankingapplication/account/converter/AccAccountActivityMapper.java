package com.springboot.bankingapplication.account.converter;

import com.springboot.bankingapplication.account.dto.AccMoneyActivityDto;
import com.springboot.bankingapplication.account.entity.AccAccountActivity;
import com.springboot.bankingapplication.account.service.entityservice.AccAccountEntityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class AccAccountActivityMapper {

    @Autowired
    AccAccountEntityService accAccountEntityService;

    @Mapping(
            target = "accAccount",
            expression = "java(accAccountEntityService.findByIdWithControl(accAccountActivity.getAccAccountId()))"
    )
    public abstract AccMoneyActivityDto convertToAccMoneyActivityDto(AccAccountActivity accAccountActivity);

}
