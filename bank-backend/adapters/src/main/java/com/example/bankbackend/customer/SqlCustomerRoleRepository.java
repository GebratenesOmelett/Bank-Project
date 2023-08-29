package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlCustomerRoleRepository extends JpaRepository<CustomerRole, Integer> {
    Optional<CustomerRole> findByRole(String role);
    CustomerRole save(CustomerRole role);
}
@Repository
class CustomerRoleRepositoryImpl implements CustomerRoleRepository{

    SqlCustomerRoleRepository repository;
    public CustomerRoleRepositoryImpl(SqlCustomerRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CustomerRole> findByRole(String role) {
        return repository.findByRole(role);
    }

    @Override
    public CustomerRole save(CustomerRole role) {
        return repository.save(role);
    }
}
