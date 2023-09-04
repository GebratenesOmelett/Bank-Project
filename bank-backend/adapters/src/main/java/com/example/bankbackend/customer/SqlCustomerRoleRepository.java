package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlCustomerRoleRepository extends JpaRepository<CustomerRoleSnapshot, Integer> {
    Optional<CustomerRoleSnapshot> findByRole(String role);
    CustomerRoleSnapshot save(CustomerRoleSnapshot role);
}
@Repository
class CustomerRoleRepositoryImpl implements CustomerRoleRepository{

    SqlCustomerRoleRepository repository;
    public CustomerRoleRepositoryImpl(SqlCustomerRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CustomerRole> findByRole(String role) {
        return repository.findByRole(role).map(CustomerRole::restore);
    }

    @Override
    public CustomerRole save(CustomerRole role) {
        return CustomerRole.restore(repository.save(role.getSnapshot()));
    }
}
