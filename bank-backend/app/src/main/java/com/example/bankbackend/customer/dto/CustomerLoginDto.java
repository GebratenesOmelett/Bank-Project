package com.example.bankbackend.customer.dto;

import lombok.Getter;

@Getter
public class CustomerLoginDto {
    private final String email;
    private final String password;
    public CustomerLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
