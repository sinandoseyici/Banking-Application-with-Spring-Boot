package com.springboot.bankingapplication.account.service;

import com.springboot.bankingapplication.account.converter.AccAccountMapperAbstract;
import com.springboot.bankingapplication.account.converter.AccAccountMapper;
import com.springboot.bankingapplication.account.dto.*;
import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.account.service.entityservice.AccAccountActivityEntityService;
import com.springboot.bankingapplication.account.service.entityservice.AccAccountEntityService;
import com.springboot.bankingapplication.account.service.entityservice.AccMoneyTransferEntityService;
import com.springboot.bankingapplication.account.util.AccAccountUtil;
import com.springboot.bankingapplication.generic.enums.EnumGenStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccAccountService {

    private final AccAccountEntityService accAccountEntityService;
    private final AccMoneyTransferEntityService accMoneyTransferEntityService;
    private final AccAccountActivityEntityService accAccountActivityEntityService;
    private final AccAccountMapperAbstract accACcountMapperAbstract;

    public List<AccAccountDto> findAll(){

        List<AccAccount> accAccountList = accAccountEntityService.findAll();

        List<AccAccountDto> accAccountDtoList = AccAccountMapper.INSTANCE.convertToAccAccountDtoList(accAccountList);

        return accAccountDtoList;
    }

    public AccAccountDto findById(Long id){

        AccAccount accAccount = accAccountEntityService.findById(id).orElseThrow();

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

    public AccAccountDto save(AccAccountSaveRequestDto accAccountSaveRequestDto){

        String ibanNo = AccAccountUtil.getIbanNo();

        AccAccount accAccount = accACcountMapperAbstract.convertToAccAccount(accAccountSaveRequestDto);
        accAccount.setIbanNo(ibanNo);
        accAccount.setStatus(EnumGenStatus.ACTIVE);
        accAccount.setCurrentBalance(BigDecimal.ZERO);
        accAccount = accAccountEntityService.save(accAccount);

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

    public void cancel(Long id) {

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(id);

        accAccount.setStatus(EnumGenStatus.PASSIVE);
        accAccount.setCancelDate(new Date());
        accAccountEntityService.save(accAccount);
    }

}
