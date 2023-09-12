package com.example.bankbackend.transfer;

import com.example.bankbackend.transfer.exceptions.TransferNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class TransferControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = TransferNotFoundException.class)
    ResponseEntity<Object> TransferNotFound(RuntimeException ex, WebRequest request){
        String response = ex.getMessage();
        return  handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
