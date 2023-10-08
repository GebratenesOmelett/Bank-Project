package com.example.bankbackend.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public <T> CustomerNotFoundException(T notFound) {
        super("There is no customer with : "+ notFound);
    }
}
