package com.example.bankbackend.customer;


import com.example.bankbackend.role.query.SimpleRoleQueryDto;
import com.example.bankbackend.transfer.Transfer;
import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.data.annotation.PersistenceConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<SimpleRoleQueryDto> roleSet;

    @OneToMany(mappedBy = "customerIdFrom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transfer> transferSet;
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

    CustomerDto toDto(){
        return CustomerDto.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withPasswordRepeat(password)
                .build();

    }
}
