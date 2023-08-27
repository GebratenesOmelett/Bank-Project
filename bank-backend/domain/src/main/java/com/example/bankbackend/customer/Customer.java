package com.example.bankbackend.customer;
import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;
import jakarta.persistence.*;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@ToString
@Entity
@Table(name = "customers")
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal funds;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<CustomerRole> roleSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SimpleTransferQueryEntity> transferSet;
    private boolean enabled;

    protected Customer() {
    }

    Customer(String firstName, String lastName, BigDecimal funds, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.funds = funds;
        this.password = password;
        this.email = email;
        this.roleSet = new HashSet<>();
        this.transferSet = new HashSet<>();
        this.enabled = true;
    }
    void addRole(CustomerRole role){
        roleSet.add(role);
    }
    void addTransfer(SimpleTransferQueryEntity transfer){
        transferSet.add(transfer);
    }
}
