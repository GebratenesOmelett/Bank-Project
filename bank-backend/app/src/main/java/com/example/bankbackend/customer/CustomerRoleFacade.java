package com.example.bankbackend.customer;

import com.example.bankbackend.customer.CustomerRole;
import com.example.bankbackend.customer.CustomerRoleRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CustomerRoleFacade {

    CustomerRoleRepository roleRepository;

    public CustomerRoleFacade(CustomerRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public CustomerRole findRole(String roleName){
        return roleRepository.findByRole(roleName)
                .orElseThrow(() -> new NoSuchElementException("There is no role with such name : " + roleName));
    }
}
