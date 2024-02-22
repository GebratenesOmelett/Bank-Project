package com.example.bankbackend.customer.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CustomerAuthDto {
    final Integer id;
    final String firstName;
    final String lastName;
    final String email;
    final BigDecimal funds;
    final String token;
    final String expiresIn;

    public CustomerAuthDto(Integer id, String firstName, String lastName, String email, BigDecimal funds, String token, String expiresIn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.funds = funds;
        this.token = token;
        this.expiresIn = expiresIn;
    }

}
