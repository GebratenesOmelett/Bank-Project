package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.example.bankbackend.customer.dto.CustomerDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.HashSet;


class CustomerFactory {
    CustomerRoleFacade roleFacade;
    PasswordEncoder encoder;

    public CustomerFactory(CustomerRoleFacade roleFacade, BCryptPasswordEncoder encoder) {
        this.roleFacade = roleFacade;
        this.encoder = encoder;
    }

    Customer from(CustomerCreateDto customerDto) {
        return attachDefaultRole(Customer.restore(CustomerSnapshot.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .funds(new BigDecimal(1000))
                .password(encoder.encode(customerDto.getPassword()))
                .email(customerDto.getEmail())
                .roleSet(new HashSet<>())
                .transferSet(new HashSet<>())
                .enabled(true)
                .build()));

    }

    public Customer attachDefaultRole(Customer customer) {
        customer.getSnapshot().addRole(roleFacade.findRole("ROLE_USER").getSnapshot());
        return customer;
    }
}
