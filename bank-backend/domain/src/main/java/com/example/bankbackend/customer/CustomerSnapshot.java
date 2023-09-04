package com.example.bankbackend.customer;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;


import java.math.BigDecimal;
import java.util.HashSet;
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

    CustomerSnapshot(final String firstName,final String lastName,final BigDecimal funds,final String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.funds = funds;
        this.password = password;
        this.email = email;
        this.roleSet = new HashSet<>();
        this.transferSet = new HashSet<>();
        this.enabled = true;
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

    void setId(int id) {
        this.id = id;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    void setPassword(String password) {
        this.password = password;
    }

    void setEmail(String email) {
        this.email = email;
    }

    void setRoleSet(Set<CustomerRole> roleSet) {
        this.roleSet = roleSet;
    }

    void setTransferSet(Set<SimpleTransferQueryEntity> transferSet) {
        this.transferSet = transferSet;
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
