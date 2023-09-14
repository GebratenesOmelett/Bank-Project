package com.example.bankbackend.customer.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class CustomerValidationException extends RuntimeException{
    public CustomerValidationException(BindingResult errors){
        super(errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList().get(0));
    }
}
