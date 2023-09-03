package com.example.bankbackend.customer.dto;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class SimpleCustomerEntitySnapshot {
    private int id;
    private String firstName;
    private String lastName;
    private Set<SimpleTransferQueryEntity> transferSet;

    public SimpleCustomerEntitySnapshot() {
    }

    SimpleCustomerEntitySnapshot(final int id,
                                        final String firstName,
                                        final String lastName,
                                        final Set<SimpleTransferQueryEntity> transferSet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.transferSet = transferSet;
    }

    int getId() {
        return id;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    Set<SimpleTransferQueryEntity> getTransferSet() {
        return transferSet;
    }
}
