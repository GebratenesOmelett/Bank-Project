package com.example.bankbackend.transfer.exceptions;

public class TransferNotFoundException extends RuntimeException{
    public <T> TransferNotFoundException(T notFound){
        super("There is no transfer with : "+ notFound);
    }
}
