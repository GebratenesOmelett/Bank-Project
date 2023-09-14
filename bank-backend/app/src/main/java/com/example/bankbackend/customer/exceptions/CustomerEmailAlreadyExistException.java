package com.example.bankbackend.customer.exceptions;

public class CustomerEmailAlreadyExistException extends RuntimeException{
    public CustomerEmailAlreadyExistException(String email){
        super("Customer with that email already exists: " + email);
    }
}
