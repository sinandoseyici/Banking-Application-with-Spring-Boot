package com.springboot.bankingapplication.account.service;

import com.springboot.bankingapplication.account.converter.AccACcountMapperAbstract;
import com.springboot.bankingapplication.account.converter.AccAccountMapper;
import com.springboot.bankingapplication.account.converter.AccMoneyTransferMapper;
import com.springboot.bankingapplication.account.dto.*;
import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.account.entity.AccAccountActivity;
import com.springboot.bankingapplication.account.entity.AccMoneyTransfer;
import com.springboot.bankingapplication.account.enums.EnumAccActivityType;
import com.springboot.bankingapplication.account.service.entityservice.AccAccountActivityEntityService;
import com.springboot.bankingapplication.account.service.entityservice.AccAccountEntityService;
import com.springboot.bankingapplication.account.service.entityservice.AccMoneyTransferEntityService;
import com.springboot.bankingapplication.generic.EnumGenStatus;
import com.springboot.bankingapplication.generic.util.StringUtil;
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

    public void cancel(Long id) {

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(id);

        accAccount.setStatus(EnumGenStatus.PASSIVE);
        accAccount.setCancelDate(new Date());
        accAccountEntityService.save(accAccount);
    }

    public void transferMoney(AccMoneyTransferRequestDto accMoneyTransferRequestDto){

        AccMoneyTransfer accMoneyTransfer = AccMoneyTransferMapper.INSTANCE.convertToAccMoneyTransfer(accMoneyTransferRequestDto);
        accMoneyTransfer.setTransactionDate(new Date());

        AccAccount accAccountFrom = accAccountEntityService.findByIdWithControl(accMoneyTransferRequestDto.getAccAccountIdFrom());
        AccAccount accAccountTo = accAccountEntityService.findByIdWithControl(accMoneyTransferRequestDto.getAccAccountIdTo());

        validateCurrencyTypes(accAccountFrom, accAccountTo);

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

        moneyOut(accMoneyActivityDtoFrom);
        moneyIn(accMoneyActivityDtoTo);

        accMoneyTransfer = accMoneyTransferEntityService.save(accMoneyTransfer);
    }

    private void moneyIn(AccMoneyActivityDto accMoneyActivityDto){

        AccAccount accAccount = accMoneyActivityDto.getAccAccount();

        BigDecimal newBalance = calculateNewBalanceForMoneyIn(accMoneyActivityDto.getAmount(), accAccount.getCurrentBalance());

        createAccountActivity(accMoneyActivityDto.getAccAccount().getId(), accMoneyActivityDto.getAmount(), newBalance, accMoneyActivityDto.getActivityType());

        updateCurrentBalance(accAccount, newBalance);
    }

    private void moneyOut(AccMoneyActivityDto accMoneyActivityDto){

        AccAccount accAccount = accMoneyActivityDto.getAccAccount();

        BigDecimal newBalance = calculateAndControlNewBalanceForMoneyOut(accMoneyActivityDto.getAmount(), accAccount.getCurrentBalance());

        createAccountActivity(accAccount.getId(), accMoneyActivityDto.getAmount(), newBalance, accMoneyActivityDto.getActivityType());

        updateCurrentBalance(accAccount, newBalance);
    }

    private void validateCurrencyTypes(AccAccount accAccountFrom, AccAccount accAccountTo){
        if (!accAccountFrom.getCurrencyType().equals(accAccountTo.getCurrencyType())){
            throw new RuntimeException("Currency types did not match. It must be the same!");
        }
    }

    private void updateCurrentBalance(AccAccount accAccountFrom, BigDecimal newBalance) {
        accAccountFrom.setCurrentBalance(newBalance);
        accAccountFrom = accAccountEntityService.save(accAccountFrom);
    }

    private void createAccountActivity(Long accAccountId, BigDecimal amount, BigDecimal newBalance, EnumAccActivityType activityType){
        AccAccountActivity accAccountActivity = new AccAccountActivity();

        accAccountActivity.setAccActivityType(activityType);
        accAccountActivity.setAmount(amount);
        accAccountActivity.setAccACcountId(accAccountId);
        accAccountActivity.setCurrentBalance(newBalance);
        accAccountActivity.setTransactionDate(new Date());

        accAccountActivity = accAccountActivityEntityService.save(accAccountActivity);
    }

    private BigDecimal calculateAndControlNewBalanceForMoneyOut(BigDecimal amount, BigDecimal currentBalance){
        BigDecimal newBalance = calculateNewBalanceForMoneyOut(amount, currentBalance);
        validateBalance(newBalance);
        return newBalance;
    }

    private void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Insufficent Balance!");
        }
    }

    private BigDecimal calculateNewBalanceForMoneyOut(BigDecimal amount, BigDecimal currentBalance){
        BigDecimal newBalance = currentBalance.subtract(amount);
        return newBalance;
    }

    private BigDecimal calculateNewBalanceForMoneyIn(BigDecimal amount, BigDecimal currentBalance){
        BigDecimal newBalance = currentBalance.add(amount);
        return newBalance;
    }

    public void withdraw(AccMoneyActivityRequestDto accMoneyActivityRequestDto){

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityRequestDto.getAccAccountId());

        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccount(accAccount)
                .amount(accMoneyActivityRequestDto.getAmount())
                .activityType(EnumAccActivityType.WITHDRAW)
                .build();

        moneyOut(accMoneyActivityDto);

    }

    public void deposit(AccMoneyActivityRequestDto accMoneyActivityRequestDto){
        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityRequestDto.getAccAccountId());

        AccMoneyActivityDto accMoneyActivityDto = AccMoneyActivityDto.builder()
                .accAccount(accAccount)
                .amount(accMoneyActivityRequestDto.getAmount())
                .activityType(EnumAccActivityType.DEPOSIT)
                .build();

        moneyIn(accMoneyActivityDto);

    }
}
