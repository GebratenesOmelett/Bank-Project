package com.example.bankbackend.customer;


import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.role.Role;
import com.example.bankbackend.role.dto.RoleDto;
import com.example.bankbackend.transfer.query.SimpleTransferQueryEntity;
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
    private Set<Role> roleSet;

    @OneToMany(mappedBy = "customerIdFrom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    CustomerDto toDto(){
        return CustomerDto.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withPasswordRepeat(password)
                .build();

    }
    void addRole(Role role){
        roleSet.add(role);
    }
}
