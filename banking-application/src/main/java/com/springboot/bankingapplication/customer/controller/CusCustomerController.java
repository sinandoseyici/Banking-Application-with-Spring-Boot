package com.springboot.bankingapplication.customer.controller;

import com.springboot.bankingapplication.customer.dto.CusCustomerDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerSaveRequestDto;
import com.springboot.bankingapplication.customer.dto.CusCustomerUpdateRequestDto;
import com.springboot.bankingapplication.customer.service.CusCustomerService;
import com.springboot.bankingapplication.generic.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CusCustomerController {

    private final CusCustomerService cusCustomerService;

    @GetMapping
    public ResponseEntity findAll(){

        List<CusCustomerDto> cusCustomerDtoList = cusCustomerService.findAll();

        return ResponseEntity.ok(RestResponse.of(cusCustomerDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        CusCustomerDto cusCustomerDto = cusCustomerService.findById(id);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        cusCustomerService.delete(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @PutMapping
    public ResponseEntity update(@RequestBody CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto){

        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }

}
