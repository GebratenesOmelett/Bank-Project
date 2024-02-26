package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlCustomerQueryRepository extends JpaRepository<CustomerSnapshot, Integer> {

    Optional<CustomerDto> findDtoByEmail(String email);
    Optional<CustomerSnapshot> findCustomerByEmail(String email);

}
@Repository
class CustomerQueryRepositoryImpl implements CustomerQueryRepository{

    private SqlCustomerQueryRepository repository;

    public CustomerQueryRepositoryImpl(SqlCustomerQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CustomerDto> findDtoByEmail(String email) {
        return repository.findDtoByEmail(email);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return repository.findCustomerByEmail(email)
                .map(Customer::restore);
    }
    @Override
    public Optional<CustomerSnapshot> findCustomerSnapshotByEmail(String email) {
        return repository.findCustomerByEmail(email);
    }
}

