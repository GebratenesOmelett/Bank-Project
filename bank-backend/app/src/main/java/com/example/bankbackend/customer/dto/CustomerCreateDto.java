package com.example.bankbackend.customer.dto;

import com.example.bankbackend.customer.validation.PasswordRepeatValidation;

@PasswordRepeatValidation
public class CustomerCreateDto {

    private final String firstName;

    private final String lastName;

    private final String password;

    private final String passwordRepeat;

    private final String email;

    public CustomerCreateDto(String firstName, String lastName, String password, String passwordRepeat, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public String getEmail() {
        return email;
    }
}
