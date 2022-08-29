package com.springboot.bankingapplication.account.converter;

import com.springboot.bankingapplication.account.dto.AccAccountDto;
import com.springboot.bankingapplication.account.dto.AccAccountSaveRequestDto;
import com.springboot.bankingapplication.account.entity.AccAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccAccountMapper {

    AccAccountMapper INSTANCE = Mappers.getMapper(AccAccountMapper.class);

    @Mapping(target = "cusCustomerId", source = "cusCustomer.id")
    AccAccountDto convertToAccAccountDto(AccAccount accAccount);

    List<AccAccountDto> convertToAccAccountDtoList(List<AccAccount> accAccountList);

    AccAccount convertToAccAccount(AccAccountSaveRequestDto accAccountSaveRequestDto);
}
