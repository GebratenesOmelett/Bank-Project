package com.example.bankbackend.role.query;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class SimpleRoleQueryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String role;
    public SimpleRoleQueryDto() {
    }
    public SimpleRoleQueryDto(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
