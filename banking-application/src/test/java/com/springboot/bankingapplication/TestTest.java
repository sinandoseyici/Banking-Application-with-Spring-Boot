package com.springboot.bankingapplication;

import com.springboot.bankingapplication.account.dto.AccAccountDto;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

public class TestTest {

    private static AccAccountDto accAccountDto;

    @BeforeAll
    public static void setup(){
        System.out.println("Before All");
        initAccAccountDto();
    }

    @BeforeEach
    public void beforeEach(){
        // initAccAccountDto();
        System.out.println("Before Each");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("After Each");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("After All");
    }

    @Test
    public void test1(){
        System.out.println("test-1");
        accAccountDto.setIbanNo("0");
        System.out.println(accAccountDto.getIbanNo());
    }

    @Test
    public void test2(){
        System.out.println("test-2");
        System.out.println(accAccountDto.getIbanNo());
    }

    private static void initAccAccountDto(){
        accAccountDto = AccAccountDto.builder()
                .id(1L)
                .cusCustomerId(1L)
                .ibanNo("123456789123456789123456")
                .currentBalance(BigDecimal.ZERO)
                .build();
    }

}
