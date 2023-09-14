package com.example.bankbackend.transfer.exceptions;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class TransferValidationException extends RuntimeException{
    public TransferValidationException(BindingResult errors){
        super(errors.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList().get(0));
    }
}
