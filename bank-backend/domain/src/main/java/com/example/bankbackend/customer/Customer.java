package com.example.bankbackend.customer;

import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

class Customer {
    static Customer restore(CustomerSnapshot snapshot){
        return new Customer(snapshot.getId(),
                snapshot.getFirstName(),
                snapshot.getLastName(),
                snapshot.getFunds(),
                snapshot.getPassword(),
                snapshot.getEmail(),
                snapshot.getRoleSet().stream()
                        .map(CustomerRole::restore)
                        .collect(Collectors.toSet()),
                snapshot.getTransferSet().stream()
                        .map(SimpleTransferQueryEntity::restore)
                        .collect(Collectors.toSet()),
                snapshot.isEnabled()
        );
    }

    private final int id;
    private final String firstName;
    private final String lastName;
    private final BigDecimal funds;
    private final String password;
    private final String email;
    private final Set<CustomerRole> roleSet;
    private final Set<SimpleTransferQueryEntity> transferSet;
    private final boolean enabled;
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
        return CustomerSnapshot.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .funds(funds)
                .password(password)
                .email(email)
                .transferSet(transferSet.stream()
                        .map(SimpleTransferQueryEntity::getSnapshot).collect(Collectors.toSet()))
                .roleSet(roleSet.stream().
                        map(CustomerRole::getSnapshot)
                        .collect(Collectors.toSet()))
                .enabled(enabled)
                .build();
    }

    void addRole(CustomerRole role){
        roleSet.add(role);
    }
    void addTransfer(SimpleTransferQueryEntity transfer){
        transferSet.add(transfer);
    }


}
