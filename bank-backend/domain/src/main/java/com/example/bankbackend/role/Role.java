package com.example.bankbackend.role;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;

    protected Role() {
    }
    Role(String role) {
        this.role = role;
    }

}
