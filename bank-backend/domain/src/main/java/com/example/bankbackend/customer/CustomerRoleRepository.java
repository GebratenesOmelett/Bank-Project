package com.example.bankbackend.customer;

import com.example.bankbackend.customer.CustomerRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CustomerRoleRepository extends JpaRepository<CustomerRole, Integer> {
    Optional<CustomerRole> findByRole(String role);
}
