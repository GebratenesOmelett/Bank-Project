package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerRoleCreateDto;


import java.util.NoSuchElementException;


public class CustomerRoleFacade {

    CustomerRoleRepository roleRepository;
    CustomerRoleFactory customerRoleFactory;

    public CustomerRoleFacade(CustomerRoleRepository roleRepository, CustomerRoleFactory customerRoleFactory) {
        this.roleRepository = roleRepository;
        this.customerRoleFactory = customerRoleFactory;
    }

    public CustomerRole findRole(String roleName){
        return roleRepository.findByRole(roleName)
                .orElseThrow(() -> new NoSuchElementException("There is no role with such name : " + roleName));
    }
    public CustomerRole save(CustomerRoleCreateDto customerRoleCreateDto){
        return roleRepository.save(customerRoleFactory.from(customerRoleCreateDto));
    }
}
