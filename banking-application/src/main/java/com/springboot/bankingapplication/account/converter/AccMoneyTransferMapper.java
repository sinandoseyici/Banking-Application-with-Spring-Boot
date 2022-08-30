package com.springboot.bankingapplication.account.converter;

import com.springboot.bankingapplication.account.dto.AccMoneyTransferRequestDto;
import com.springboot.bankingapplication.account.entity.AccMoneyTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccMoneyTransferMapper {

    AccMoneyTransferMapper INSTANCE = Mappers.getMapper(AccMoneyTransferMapper.class);

    AccMoneyTransfer convertToAccMoneyTransfer(AccMoneyTransferRequestDto accMoneyTransferRequestDto);
}
