package com.example.bankbackend.customer;
import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

class Customer {
    static Customer restore(CustomerSnapshot snapshot){
        return new Customer(snapshot.getId(),
                snapshot.getFirstName(),
                snapshot.getLastName(),
                snapshot.getFunds(),
                snapshot.getPassword(),
                snapshot.getEmail(),
                snapshot.getRoleSet(),
                snapshot.getTransferSet(),
                snapshot.isEnabled()
        );
    }

    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal funds;
    private String password;
    private String email;
    private Set<CustomerRole> roleSet;
    private Set<SimpleTransferQueryEntity> transferSet;
    private boolean enabled;

    protected Customer() {
    }

    public Customer(int id,
                    String firstName,
                    String lastName,
                    BigDecimal funds,
                    String password,
                    String email,
                    Set<CustomerRole> roleSet,
                    Set<SimpleTransferQueryEntity> transferSet,
                    boolean enabled) {
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
    CustomerSnapshot getSnapshot(){
        return new CustomerSnapshot(firstName,lastName,funds,password,email);
    }

    void addRole(CustomerRole role){
        roleSet.add(role);
    }
    void addTransfer(SimpleTransferQueryEntity transfer){
        transferSet.add(transfer);
    }
}
