package com.example.bankbackend.customer;

import lombok.ToString;

@ToString
class CustomerRoleSnapshot {

    private int id;
    private String role;

    public CustomerRoleSnapshot() {
    }

    CustomerRoleSnapshot(final int id, final String role) {
        this.id = id;
        this.role = role;
    }

    int getId() {
        return id;
    }

    String getRole() {
        return role;
    }

    void setId(int id) {
        this.id = id;
    }

    void setRole(String role) {
        this.role = role;
    }
}
