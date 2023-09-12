package com.example.bankbackend.customer;

import com.example.bankbackend.customer.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class CustomerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = CustomerNotFoundException.class)
    ResponseEntity<Object> CustomerNotFound(RuntimeException ex, WebRequest request){
        String response = ex.getMessage();
        return  handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
