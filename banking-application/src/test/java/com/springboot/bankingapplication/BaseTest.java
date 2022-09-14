package com.springboot.bankingapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.bankingapplication.generic.response.RestResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class BaseTest {

    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult result) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException{

        RestResponse restResponse = getRestResponse(result);

        return restResponse.isSuccess();
    }

    protected RestResponse getRestResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException{

        RestResponse restResponse = objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);

        return restResponse;
    }
}
