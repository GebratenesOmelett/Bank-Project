package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class CustomerFactory {
    CustomerRoleFacade roleFacade;

    public CustomerFactory(CustomerRoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    Customer from(CustomerDto customerDto){
        return attachDefaultRole(new Customer(customerDto.getFirstName(),
                customerDto.getLastName(),
                new BigDecimal(1000),
                customerDto.getPassword(),
                customerDto.getEmail()));

    }

    public Customer attachDefaultRole(Customer customer){
        customer.addRole(roleFacade.findRole("ROLE_USER"));
        return customer;
    }
}
