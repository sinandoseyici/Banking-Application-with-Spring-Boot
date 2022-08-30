package com.springboot.bankingapplication.generic.exceptions;

import com.springboot.bankingapplication.generic.response.ExceptionResponse;
import com.springboot.bankingapplication.generic.response.RestResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

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

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest webRequest){

        Date errorDate = new Date();
        String message = "Validation failed!";
        String detail = getDetails(exception);

        return getResponseEntity(errorDate, message, detail, HttpStatus.BAD_REQUEST);
    }

    private String getDetails(MethodArgumentNotValidException exception){
        String detail = "";
        List<ObjectError> objectErrorList = exception.getBindingResult().getAllErrors();

        for(ObjectError objectError : objectErrorList){

            String eachDetail = getEachDetail(objectErrorList, objectError);

            detail = detail + eachDetail;
        }

        return detail;
    }

    private String getEachDetail(List<ObjectError> objectErrorList, ObjectError objectError){

        int index = getIndexAsNumber(objectErrorList,objectError);
        String fieldName = getFieldName(objectError.getCodes());

        String eachDetail = index + ". ";
        eachDetail = eachDetail + fieldName + " " + objectError.getDefaultMessage() + " ";
        return eachDetail;
    }

    private int getIndexAsNumber(List<ObjectError> objectErrorList, ObjectError objectError){
        int i = objectErrorList.indexOf(objectError) + 1;
        return  i;
    }

    private String getFieldName(String[] codes){

        String fieldName = "";

        if (codes != null && codes.length > 0){
            fieldName = codes[0];
        }

        return fieldName;
    }
    private ResponseEntity<Object> getResponseEntity(Date errorDate, String message, String detail, HttpStatus status){
        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, message, detail);

        RestResponse<ExceptionResponse> restResponse = RestResponse.error(exceptionResponse);
        restResponse.setMessages(message);

        return new ResponseEntity<>(restResponse, status);
    }


}
