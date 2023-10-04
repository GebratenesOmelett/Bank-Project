package com.example.bankbackend.customer.dto;

import com.example.bankbackend.customer.validation.PasswordRepeatValidation;
import com.fasterxml.jackson.annotation.JsonProperty;

@PasswordRepeatValidation
public class CustomerCreateDto {
    @JsonProperty("firstName")
    private final String firstName;
    @JsonProperty("lastName")
    private final String lastName;
    @JsonProperty("password")
    private final String password;
    @JsonProperty("passwordRepeat")
    private final String passwordRepeat;
    @JsonProperty("email")
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
