package com.springboot.bankingapplication.generic.exceptions;

import com.springboot.bankingapplication.generic.response.ExceptionResponse;
import com.springboot.bankingapplication.generic.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest){

        Date errorDate = new Date();
        String message = exception.getMessage();
        String detail = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public final ResponseEntity<Object> handleBusinessExceptions(BusinessException businessException, WebRequest webRequest){

        Date errorDate = new Date();
        String message = businessException.getBaseErrorMessage().getMessage();
        String detail = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public final ResponseEntity<Object> handleItemNotFoundExceptions(ItemNotFoundException itemNotFoundException, WebRequest webRequest){

        Date errorDate = new Date();
        String message = itemNotFoundException.getBaseErrorMessage().getMessage();
        String detail = webRequest.getDescription(false);

        return getResponseEntity(errorDate, message, detail, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> getResponseEntity(Date errorDate, String message, String detail, HttpStatus status){
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, message, detail);

        RestResponse<ExceptionResponse> restResponse = RestResponse.error(exceptionResponse);
        restResponse.setMessages(message);

        return new ResponseEntity<>(restResponse, status);
    }


}
