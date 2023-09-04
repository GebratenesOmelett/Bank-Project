package com.example.bankbackend.customer;
class CustomerRoleSnapshot {

    private long id;
    private String role;

    public CustomerRoleSnapshot() {
    }

    CustomerRoleSnapshot(final long id, final String role) {
        this.id = id;
        this.role = role;
    }

    long getId() {
        return id;
    }

    String getRole() {
        return role;
    }

    void setId(long id) {
        this.id = id;
    }

    void setRole(String role) {
        this.role = role;
    }
}
