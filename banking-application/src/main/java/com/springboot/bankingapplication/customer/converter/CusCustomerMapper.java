package com.springboot.bankingapplication.customer.converter;

import com.springboot.bankingapplication.customer.dto.CusCustomerDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerSaveRequestDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerUpdateRequestDto;
import com.springboot.bankingapplication.customer.entity.CusCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CusCustomerMapper {

    CusCustomerMapper INSTANCE = Mappers.getMapper(CusCustomerMapper.class);

    CusCustomerDto convertToCusCustomerDto(CusCustomer cusCustomer);

    List<CusCustomerDto> convertToCusCustomerDtoList(List<CusCustomer> cusCustomerList);

    CusCustomer convertToCusCustomer(CusCustomerSaveRequestDto cusCustomerSaveRequestDto);

    CusCustomer convertToCusCustomer(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto);

}
