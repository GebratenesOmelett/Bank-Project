package com.example.bankbackend.customer.dto;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.Set;

public class SimpleCustomerEntity {

    private int id;
    private String firstName;
    private String lastName;

    private Set<SimpleTransferQueryEntity> transferSet;


}
