package com.example.bankbackend.customer;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;


import java.math.BigDecimal;
import java.util.Set;

class CustomerSnapshot {

    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal funds;
    private String password;
    private String email;
    private Set<CustomerRole> roleSet;
    private Set<SimpleTransferQueryEntity> transferSet;
    private boolean enabled;

    public CustomerSnapshot() {
    }

    CustomerSnapshot(int id, String firstName, String lastName, BigDecimal funds, String password, String email, Set<CustomerRole> roleSet, Set<SimpleTransferQueryEntity> transferSet, boolean enabled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.funds = funds;
        this.password = password;
        this.email = email;
        this.roleSet = roleSet;
        this.transferSet = transferSet;
        this.enabled = enabled;
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

    BigDecimal getFunds() {
        return funds;
    }

    String getPassword() {
        return password;
    }

    String getEmail() {
        return email;
    }

    Set<CustomerRole> getRoleSet() {
        return roleSet;
    }

    Set<SimpleTransferQueryEntity> getTransferSet() {
        return transferSet;
    }

    boolean isEnabled() {
        return enabled;
    }
}
