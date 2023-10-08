package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlCustomerRepository extends JpaRepository<CustomerSnapshot, Integer> {
    Optional<CustomerSnapshot> findCustomerById(int id);

    CustomerSnapshot save(CustomerSnapshot entity);
}



@Repository
class CustomerRepositoryImpl implements CustomerRepository {

    private SqlCustomerRepository repository;

    public CustomerRepositoryImpl(SqlCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Customer> findCustomerById(int id) {
        return repository.findCustomerById(id)
                .map(Customer::restore);
    }

    @Override
    public Customer save(Customer entity) {
        System.out.println(entity.getSnapshot());
        return Customer.restore(repository.save(entity.getSnapshot()));
    }


}


