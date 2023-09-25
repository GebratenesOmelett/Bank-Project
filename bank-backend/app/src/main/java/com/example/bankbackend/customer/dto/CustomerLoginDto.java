package com.example.bankbackend.customer.dto;

public class CustomerLoginDto {
    private final String email;
    private final String password;
    public CustomerLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
