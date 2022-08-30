package com.springboot.bankingapplication.account.controller;

import com.springboot.bankingapplication.account.dto.*;
import com.springboot.bankingapplication.account.service.AccAccountActivityService;
import com.springboot.bankingapplication.account.service.AccAccountService;
import com.springboot.bankingapplication.account.service.AccMoneyTransferService;
import com.springboot.bankingapplication.generic.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity findAll(){
        List<AccAccountDto> accAccountDtoList = accAccountService.findAll();

        return ResponseEntity.ok(RestResponse.of(accAccountDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        AccAccountDto accAccountDto = accAccountService.findById(id);

        return ResponseEntity.ok(RestResponse.of(accAccountDto));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody AccAccountSaveRequestDto accAccountSaveRequestDto){

        AccAccountDto accAccountDto = accAccountService.save(accAccountSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(accAccountDto));
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity cancel(@PathVariable Long id){

        accAccountService.cancel(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @PostMapping("/money-transfer")
    public ResponseEntity transferMoney(@RequestBody AccMoneyTransferRequestDto accMoneyTransferRequestDto){
        AccMoneyTransferDto accMoneyTransferDto = accMoneyTransferService.transferMoney(accMoneyTransferRequestDto);

        return ResponseEntity.ok(RestResponse.of(accMoneyTransferDto));
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestBody AccMoneyActivityRequestDto accMoneyActivityRequestDto){
        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityService.withdraw(accMoneyActivityRequestDto);

        return ResponseEntity.ok(RestResponse.of(accMoneyActivityDto));
    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestBody AccMoneyActivityRequestDto accMoneyActivityRequestDto){
        AccMoneyActivityDto accMoneyActivityDto = accAccountActivityService.deposit(accMoneyActivityRequestDto);

        return ResponseEntity.ok(RestResponse.of(accMoneyActivityDto));
    }

}
