package com.example.bankbackend.customer.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class SimpleCustomerQueryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public SimpleCustomerQueryEntity() {

    }
    public SimpleCustomerQueryEntity(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

}
