package com.example.bankbackend.customer.dto;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntitySnapshot;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Set;

public class SimpleCustomerEntitySnapshot {

    private int id;
    private String firstName;
    private String lastName;
    private Set<SimpleTransferQueryEntitySnapshot> transferSet;

    public SimpleCustomerEntitySnapshot() {
    }

    public SimpleCustomerEntitySnapshot(final int id,
                                 final String firstName,
                                 final String lastName,
                                 final Set<SimpleTransferQueryEntitySnapshot> transferSet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.transferSet = transferSet;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<SimpleTransferQueryEntitySnapshot> getTransferSet() {
        return transferSet;
    }

    void setId(int id) {
        this.id = id;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setTransferSet(Set<SimpleTransferQueryEntitySnapshot> transferSet) {
        this.transferSet = transferSet;
    }
}
