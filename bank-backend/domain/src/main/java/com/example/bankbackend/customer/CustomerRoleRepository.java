package com.example.bankbackend.customer;

import java.util.Optional;

interface CustomerRoleRepository {
    Optional<CustomerRole> findByRole(String role);

    CustomerRole save(CustomerRole role);
}
