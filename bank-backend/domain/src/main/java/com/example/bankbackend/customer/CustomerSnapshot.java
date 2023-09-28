package com.example.bankbackend.customer;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntitySnapshot;
import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;

@Builder
@ToString
class CustomerSnapshot {

    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal funds;
    private String password;
    private String email;
    private Set<CustomerRoleSnapshot> roleSet;
    private Set<SimpleTransferQueryEntitySnapshot> transferSet;
    private boolean enabled;

    public CustomerSnapshot() {
    }

    public CustomerSnapshot(int id, String firstName, String lastName, BigDecimal funds, String password, String email, Set<CustomerRoleSnapshot> roleSet, Set<SimpleTransferQueryEntitySnapshot> transferSet, boolean enabled) {
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

    Set<CustomerRoleSnapshot> getRoleSet() {
        return roleSet;
    }

    Set<SimpleTransferQueryEntitySnapshot> getTransferSet() {
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

    void setRoleSet(Set<CustomerRoleSnapshot> roleSet) {
        this.roleSet = roleSet;
    }

    void setTransferSet(Set<SimpleTransferQueryEntitySnapshot> transferSet) {
        this.transferSet = transferSet;
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    void addRole(CustomerRoleSnapshot role){
        roleSet.add(role);
    }
    void addTransfer(SimpleTransferQueryEntitySnapshot transfer){
        transferSet.add(transfer);
    }

}
