package com.example.bankbackend.customer;


import jakarta.persistence.*;


class CustomerRole {

    private long id;
    private String role;

    protected CustomerRole() {
    }
    CustomerRole(String role) {
        this.role = role;
    }

}
