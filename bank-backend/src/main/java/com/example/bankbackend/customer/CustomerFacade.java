package com.example.bankbackend.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerFacade {
    CustomerRepository customerRepository;
    CustomerFactory customerFactory;

    public CustomerFacade(final CustomerRepository customerRepository, final CustomerFactory customerFactory) {
        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
    }

    Optional<CustomerDto> get(int customerId){
        return customerRepository.findCustomerById(customerId)
                .map(Customer::toDto);
    }
    void create(CustomerDto customerDto){
        customerRepository.save(customerFactory.from(customerDto));
    }
}
