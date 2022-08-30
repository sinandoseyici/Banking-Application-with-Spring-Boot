package com.springboot.bankingapplication.account.service;

import com.springboot.bankingapplication.account.converter.AccMoneyTransferMapper;
import com.springboot.bankingapplication.account.dto.AccMoneyActivityDto;
import com.springboot.bankingapplication.account.dto.AccMoneyTransferDto;
import com.springboot.bankingapplication.account.dto.AccMoneyTransferRequestDto;
import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.account.entity.AccMoneyTransfer;
import com.springboot.bankingapplication.account.enums.EnumAccActivityType;
import com.springboot.bankingapplication.account.service.entityservice.AccAccountEntityService;
import com.springboot.bankingapplication.account.service.entityservice.AccMoneyTransferEntityService;
import com.springboot.bankingapplication.account.validator.AccValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AccMoneyTransferService {

    private final AccAccountActivityService accAccountActivityService;
    private final AccMoneyTransferEntityService accMoneyTransferEntityService;
    private final AccAccountEntityService accAccountEntityService;
    private final AccValidator accValidator;

    public AccMoneyTransferDto transferMoney(AccMoneyTransferRequestDto accMoneyTransferRequestDto){

        AccMoneyTransfer accMoneyTransfer = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransfer(accMoneyTransferRequestDto);
        accMoneyTransfer.setTransactionDate(new Date());

        AccAccount accAccountFrom = accAccountEntityService.findByIdWithControl(accMoneyTransferRequestDto.getAccAccountIdFrom());
        AccAccount accAccountTo = accAccountEntityService.findByIdWithControl(accMoneyTransferRequestDto.getAccAccountIdTo());

        accValidator.validateCurrencyTypes(accAccountFrom, accAccountTo);

        AccMoneyActivityDto accMoneyActivityDtoFrom = AccMoneyActivityDto.builder()
                .accAccount(accAccountFrom)
                .amount(accMoneyTransferRequestDto.getAmount())
                .activityType(EnumAccActivityType.SEND)
                .build();

        AccMoneyActivityDto accMoneyActivityDtoTo = AccMoneyActivityDto.builder()
                .accAccount(accAccountTo)
                .amount(accMoneyTransferRequestDto.getAmount())
                .activityType(EnumAccActivityType.GET)
                .build();

        accAccountActivityService.moneyOut(accMoneyActivityDtoFrom);
        accAccountActivityService.moneyIn(accMoneyActivityDtoTo);

        accMoneyTransfer = accMoneyTransferEntityService.save(accMoneyTransfer);

        AccMoneyTransferDto accMoneyTransferDto = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransferDto(accMoneyTransfer);

        return accMoneyTransferDto;
    }
}
