package com.example.bankbackend.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(int id) {
        super("There is no customer with id : "+ id);
    }
}
