package com.example.bankbackend.customer.dto;

public class CustomerLoginResponseDto {
    final String message;
    final boolean status;

    public CustomerLoginResponseDto(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
