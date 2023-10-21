package com.example.bankbackend.customer.dto;



public class CustomerRoleCreateDto {

    private final String role;

    public CustomerRoleCreateDto(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
