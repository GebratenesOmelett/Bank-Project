package com.example.bankbackend.customer;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ToString
class CustomerRoleSnapshot implements GrantedAuthority {

    private int id;
    private String role;

    public CustomerRoleSnapshot() {
    }

    CustomerRoleSnapshot(final int id, final String role) {
        this.id = id;
        this.role = role;
    }
    CustomerRoleSnapshot(final String role) {
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

    @Override
    public String getAuthority() {
        return role;
    }
}
