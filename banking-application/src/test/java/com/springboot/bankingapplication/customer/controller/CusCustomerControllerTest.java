package com.springboot.bankingapplication.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CusCustomerControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

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
                get("/api/v1/customers").content("").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        RestResponse restResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);

        boolean isSuccess = restResponse.isSuccess();

        assertTrue(isSuccess);
    }

    @Test
    void findById(){

    }

    @Test
    void save(){

    }

    @Test
    void delete(){

    }

    @Test
    void update(){

    }



}

