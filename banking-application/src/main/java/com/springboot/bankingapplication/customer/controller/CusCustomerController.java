package com.springboot.bankingapplication.customer.controller;

import com.springboot.bankingapplication.customer.dto.CusCustomerDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerSaveRequestDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerUpdateRequestDto;
import com.springboot.bankingapplication.customer.service.CusCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CusCustomerController {

    private final CusCustomerService cusCustomerService;

    @GetMapping
    public List<CusCustomerDto> findAll(){
        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();
        return cusCustomerDtoList;
    }

    @GetMapping("/{id}")
    public CusCustomerDto findById(@PathVariable Long id){
        CusCustomerDto cusCustomerDto = cusCustomerService.findById(id);
        return cusCustomerDto;
    }

    @PostMapping
    public CusCustomerDto save(@RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){
        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);
        return cusCustomerDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        cusCustomerService.delete(id);
    }

    @PutMapping
    public CusCustomerDto update(@RequestBody CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto){
        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);
        return cusCustomerDto;
    }

}
