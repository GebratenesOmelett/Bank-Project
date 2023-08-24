package com.example.bankbackend.customer;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
@Service
class CustomerFactory {
    Customer from(CustomerDto customerDto){
        Customer customer = new Customer(customerDto.getFirstName(),
                customerDto.getLastName(),
                new BigDecimal(1000),
                customerDto.getPassword(),
                customerDto.getEmail());
        return customer;
    }
}
