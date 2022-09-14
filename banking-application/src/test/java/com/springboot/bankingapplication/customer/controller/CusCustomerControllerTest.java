package com.springboot.bankingapplication.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.bankingapplication.BaseTest;
import com.springboot.bankingapplication.customer.dto.CusCustomerUpdateRequestDto;
import com.springboot.bankingapplication.generic.response.RestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CusCustomerControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/customers";
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp(){

        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void findAll() throws Exception{

        MvcResult mvcResult = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        RestResponse restResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);

        boolean isSuccess = isSuccess(mvcResult);

        assertTrue(isSuccess);
    }

    @Test
    void findById() throws Exception{

        MvcResult mvcResult = mockMvc.perform(
                get(BASE_PATH + "/1").content("1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSucccess = isSuccess(mvcResult);

        assertTrue(isSucccess);

    }

    @Test
    void save() throws Exception{

        String body = "{\n" +
                "  \"name\": \"john\",\n" +
                "  \"surname\": \"grant\",\n" +
                "  \"identityNo\": 10000000000,\n" +
                "  \"password\": \"xxxyyyzzz\"\n" +
                "}";

        MvcResult mvcResult = mockMvc.perform(
                post(BASE_PATH).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(mvcResult);
    }

    @Test
    void delete() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete(BASE_PATH + "/1102").content("").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(mvcResult);

        assertTrue(isSuccess);
    }

    @Test
    void update() throws Exception{

        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = CusCustomerUpdateRequestDto.builder()
                .id(1L)
                .name("Sinan")
                .surname("Döşeyici")
                .identityNo(12345678901L)
                .password("password")
                .build();

        String body = objectMapper.writeValueAsString(cusCustomerUpdateRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                put(BASE_PATH).content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(mvcResult);

        assertTrue(isSuccess);
    }



}

