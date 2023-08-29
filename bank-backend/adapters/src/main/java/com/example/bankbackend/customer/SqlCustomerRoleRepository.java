package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlCustomerRoleRepository extends CustomerRoleRepository, JpaRepository<CustomerRole, Integer> {
}
