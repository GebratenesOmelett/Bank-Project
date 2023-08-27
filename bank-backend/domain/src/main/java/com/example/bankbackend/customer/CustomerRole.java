package com.example.bankbackend.customer;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
class CustomerRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;

    protected CustomerRole() {
    }
    CustomerRole(String role) {
        this.role = role;
    }

}
