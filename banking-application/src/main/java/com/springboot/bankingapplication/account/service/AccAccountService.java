package com.springboot.bankingapplication.account.service;

import com.springboot.bankingapplication.account.converter.AccACcountMapperAbstract;
import com.springboot.bankingapplication.account.converter.AccAccountMapper;
import com.springboot.bankingapplication.account.dto.AccAccountDto;
import com.springboot.bankingapplication.account.dto.AccAccountSaveRequestDto;
import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.account.entity.entityservice.AccAccountEntityService;
import com.springboot.bankingapplication.generic.EnumGenStatus;
import com.springboot.bankingapplication.generic.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccAccountService {

    private final AccAccountEntityService accAccountEntityService;
    private final AccACcountMapperAbstract accACcountMapperAbstract;

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

        String ibanNo = getIbanNo();

        AccAccount accAccount = accACcountMapperAbstract.convertToAccAccount(accAccountSaveRequestDto);
        accAccount.setIbanNo(ibanNo);
        accAccount.setStatus(EnumGenStatus.ACTIVE);
        accAccount.setCurrentBalance(BigDecimal.ZERO);
        accAccount = accAccountEntityService.save(accAccount);

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

    private String getIbanNo() {
        String randomNumberAsString = StringUtil.getRandomNumberAsString(24);

        return "TR" + randomNumberAsString;
    }
}
