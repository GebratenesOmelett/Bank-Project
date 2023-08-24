package com.example.bankbackend.customer.query;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class SimpleCustomerQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    public SimpleCustomerQueryDto() {

    }
    public SimpleCustomerQueryDto(String firstName) {
        this.firstName = firstName;
    }
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
}
