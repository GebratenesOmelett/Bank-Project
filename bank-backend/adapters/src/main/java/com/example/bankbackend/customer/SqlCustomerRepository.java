package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlCustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerById(int id);

    Customer save(Customer entity);
}

interface SqlCustomerQueryRepository extends CustomerQueryRepository, JpaRepository<Customer, Integer> {
}

@Repository
class CustomerRepositoryImpl implements CustomerRepository {

    private SqlCustomerRepository repository;

    public CustomerRepositoryImpl(SqlCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Customer> findCustomerById(int id) {
        return repository.findCustomerById(id);
    }

    @Override
    public Customer save(Customer entity) {
        return repository.save(entity);
    }
}


