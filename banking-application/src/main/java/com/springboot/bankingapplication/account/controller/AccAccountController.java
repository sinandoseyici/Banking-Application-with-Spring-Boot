package com.springboot.bankingapplication.account.controller;

import com.springboot.bankingapplication.account.dto.*;
import com.springboot.bankingapplication.account.service.AccAccountActivityService;
import com.springboot.bankingapplication.account.service.AccAccountService;
import com.springboot.bankingapplication.account.service.AccMoneyTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccAccountController {

    private final AccAccountService accAccountService;
    private final AccAccountActivityService accAccountActivityService;
    private final AccMoneyTransferService accMoneyTransferService;

    @GetMapping
    public List<AccAccountDto> findAll(){
        List<AccAccountDto> accAccountDtoList = accAccountService.findAll();

        return accAccountDtoList;
    }

    @GetMapping("/{id}")
    public AccAccountDto findById(@PathVariable Long id){

        AccAccountDto accAccountDto = accAccountService.findById(id);

        return accAccountDto;
    }

    @PostMapping
    public AccAccountDto save(@RequestBody AccAccountSaveRequestDto accAccountSaveRequestDto){

        AccAccountDto accAccountDto = accAccountService.save(accAccountSaveRequestDto);

        return accAccountDto;
    }

    @PatchMapping("/cancel/{id}")
    public void cancel(@PathVariable Long id){

        accAccountService.cancel(id);
    }

    @PostMapping("/money-transfer")
    public AccMoneyTransferDto transferMoney(@RequestBody AccMoneyTransferRequestDto accMoneyTransferRequestDto){
        AccMoneyTransferDto accMoneyTransferDto = accMoneyTransferService.transferMoney(accMoneyTransferRequestDto);

        return accMoneyTransferDto;
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody AccMoneyActivityRequestDto accMoneyActivityRequestDto){
        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityService.withdraw(accMoneyActivityRequestDto);
    }

    @PostMapping("/deposit")
    public void deposit(@RequestBody AccMoneyActivityRequestDto accMoneyActivityRequestDto){
        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityService.deposit(accMoneyActivityRequestDto);
    }

}
