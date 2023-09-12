package com.example.bankbackend.transfer.exceptions;

public class TransferNotFoundException extends RuntimeException{
    public TransferNotFoundException(int id){
        super("There is no transfer with id : "+ id);
    }
}
