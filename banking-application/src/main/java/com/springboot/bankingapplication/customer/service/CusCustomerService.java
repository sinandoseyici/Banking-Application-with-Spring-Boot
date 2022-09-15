package com.springboot.bankingapplication.customer.service;

import com.springboot.bankingapplication.customer.converter.CusCustomerMapper;
import com.springboot.bankingapplication.customer.dto.CusCustomerDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerSaveRequestDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerUpdateRequestDto;
import com.springboot.bankingapplication.customer.entity.CusCustomer;
import com.springboot.bankingapplication.customer.entity.entityservice.CusCustomerEntityService;
import com.springboot.bankingapplication.customer.enums.EnumCusErrorMessage;
import com.springboot.bankingapplication.generic.enums.ErrorMessage;
import com.springboot.bankingapplication.generic.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CusCustomerService {

    private final CusCustomerEntityService cusCustomerEntityService;

    private final PasswordEncoder passwordEncoder;
    public List<CusCustomerDto> findAll(){

        List<CusCustomer> cusCustomerList = cusCustomerEntityService.findAll();

        List<CusCustomerDto> cusCustomerDtoList = CusCustomerMapper.INSTANCE.convertToCusCustomerDtoList(cusCustomerList);

        return cusCustomerDtoList;
    }

    public CusCustomerDto findById(Long id){
        Optional<CusCustomer> optionalCusCustomer = cusCustomerEntityService.findById(id);

        if (!optionalCusCustomer.isPresent()){
            return null;
        }

        CusCustomer cusCustomer = optionalCusCustomer.get();

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public CusCustomerDto findByIdWithControl(Long id){

        CusCustomer cusCustomer = cusCustomerEntityService.findById(id).orElseThrow();

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public CusCustomerDto save(CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        if (cusCustomerSaveRequestDto == null){
            throw new BusinessException(ErrorMessage.PARAMETER_CANNOT_BE_NULL);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerSaveRequestDto);

        String encodedPassword = passwordEncoder.encode(cusCustomer.getPassword());
        cusCustomer.setPassword(encodedPassword);

        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

    public void delete(Long id) {

        cusCustomerEntityService.delete(id);
    }

    public CusCustomerDto update(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto) {

        boolean isExist = cusCustomerEntityService.isExist(cusCustomerUpdateRequestDto.getId());
        if (!isExist){
            throw new BusinessException(EnumCusErrorMessage.CUSTOMER_DOES_NOT_EXIST);
        }

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerUpdateRequestDto);

        String encodedPassword = passwordEncoder.encode(cusCustomer.getPassword());
        cusCustomer.setPassword(encodedPassword);

        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        CusCustomerDto cusCustomerDto = CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);

        return cusCustomerDto;
    }

}
