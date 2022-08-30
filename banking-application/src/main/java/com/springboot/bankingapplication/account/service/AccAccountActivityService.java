package com.springboot.bankingapplication.account.service;

import com.springboot.bankingapplication.account.converter.AccAccountActivityMapper;
import com.springboot.bankingapplication.account.dto.AccMoneyActivityDto;
import com.springboot.bankingapplication.account.dto.AccMoneyActivityRequestDto;
import com.springboot.bankingapplication.account.entity.AccAccount;
import com.springboot.bankingapplication.account.entity.AccAccountActivity;
import com.springboot.bankingapplication.account.enums.EnumAccActivityType;
import com.springboot.bankingapplication.account.service.entityservice.AccAccountActivityEntityService;
import com.springboot.bankingapplication.account.service.entityservice.AccAccountEntityService;
import com.springboot.bankingapplication.account.validator.AccValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AccAccountActivityService {

    private final AccAccountEntityService accAccountEntityService;
    private final AccAccountActivityEntityService accAccountActivityEntityService;
    private final AccValidator accValidator;
    private final AccAccountActivityMapper accAccountActivityMapper;

    public AccMoneyActivityDto withdraw(AccMoneyActivityRequestDto accMoneyActivityRequestDto){

        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityRequestDto.getAccAccountId());

        AccMoneyActivityDto accMoneyActivityDtoTo = AccMoneyActivityDto.builder()
                .accAccount(accAccount)
                .amount(accMoneyActivityRequestDto.getAmount())
                .activityType(EnumAccActivityType.WITHDRAW)
                .build();

        AccAccountActivity accAccountActivity = moneyOut(accMoneyActivityDtoTo);

        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityMapper.convertToAccMoneyActivityDto(accAccountActivity);

        return accMoneyActivityDto;

    }

    public AccMoneyActivityDto deposit(AccMoneyActivityRequestDto accMoneyActivityRequestDto){
        AccAccount accAccount = accAccountEntityService.findByIdWithControl(accMoneyActivityRequestDto.getAccAccountId());

        AccMoneyActivityDto accMoneyActivityDtoTo = AccMoneyActivityDto.builder()
                .accAccount(accAccount)
                .amount(accMoneyActivityRequestDto.getAmount())
                .activityType(EnumAccActivityType.DEPOSIT)
                .build();

        AccAccountActivity accAccountActivity = moneyIn(accMoneyActivityDtoTo);

        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityMapper.convertToAccMoneyActivityDto(accAccountActivity);

        return accMoneyActivityDto;

    }

    public AccAccountActivity moneyIn(AccMoneyActivityDto accMoneyActivityDto){

        AccAccount accAccount = accMoneyActivityDto.getAccAccount();

        BigDecimal newBalance = calculateNewBalanceForMoneyIn(accMoneyActivityDto.getAmount(), accAccount.getCurrentBalance());

        AccAccountActivity accAccountActivity = createAccountActivity(accMoneyActivityDto.getAccAccount().getId(), accMoneyActivityDto.getAmount(), newBalance, accMoneyActivityDto.getActivityType());

        updateCurrentBalance(accAccount, newBalance);

        return accAccountActivity;
    }

    public AccAccountActivity moneyOut(AccMoneyActivityDto accMoneyActivityDto){

        AccAccount accAccount = accMoneyActivityDto.getAccAccount();

        BigDecimal newBalance = calculateAndControlNewBalanceForMoneyOut(accMoneyActivityDto.getAmount(), accAccount.getCurrentBalance());

        AccAccountActivity accAccountActivity = createAccountActivity(accAccount.getId(), accMoneyActivityDto.getAmount(), newBalance, accMoneyActivityDto.getActivityType());

        updateCurrentBalance(accAccount, newBalance);

        return accAccountActivity;
    }

    private void updateCurrentBalance(AccAccount accAccountFrom, BigDecimal newBalance) {
        accAccountFrom.setCurrentBalance(newBalance);
        accAccountFrom = accAccountEntityService.save(accAccountFrom);
    }

    private AccAccountActivity createAccountActivity(Long accAccountId, BigDecimal amount, BigDecimal newBalance, EnumAccActivityType activityType){
        AccAccountActivity accAccountActivity = new AccAccountActivity();

        accAccountActivity.setAccActivityType(activityType);
        accAccountActivity.setAmount(amount);
        accAccountActivity.setAccAccountId(accAccountId);
        accAccountActivity.setCurrentBalance(newBalance);
        accAccountActivity.setTransactionDate(new Date());

        accAccountActivity = accAccountActivityEntityService.save(accAccountActivity);

        return accAccountActivity;
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
}
