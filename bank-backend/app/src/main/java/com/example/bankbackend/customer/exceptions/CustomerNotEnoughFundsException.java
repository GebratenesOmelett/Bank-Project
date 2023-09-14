package com.example.bankbackend.customer.exceptions;

public class CustomerNotEnoughFundsException extends RuntimeException{
    public CustomerNotEnoughFundsException(){
        super("Not enough funds");
    }
}
