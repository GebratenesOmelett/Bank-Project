package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;


class CustomerFactory {
    CustomerRoleFacade roleFacade;

    public CustomerFactory(CustomerRoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    Customer from(CustomerDto customerDto) {
        return attachDefaultRole(Customer.restore(CustomerSnapshot.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .funds(new BigDecimal(1000))
                .password(customerDto.getPassword())
                .email(customerDto.getEmail())
                .roleSet(new HashSet<>())
                .transferSet(new HashSet<>())
                .enabled(true)
                .build()));

    }

    public Customer attachDefaultRole(Customer customer) {
        customer.addRole(roleFacade.findRole("ROLE_USER"));
        return customer;
    }
}
