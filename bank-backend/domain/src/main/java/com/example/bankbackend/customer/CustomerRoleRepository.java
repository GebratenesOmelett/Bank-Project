package com.example.bankbackend.customer;

import com.example.bankbackend.customer.CustomerRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

interface CustomerRoleRepository {
    Optional<CustomerRole> findByRole(String role);

    CustomerRole save(CustomerRole role);
}
