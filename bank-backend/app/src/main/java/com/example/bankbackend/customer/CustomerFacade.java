package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CustomerFacade {
    CustomerRepository customerRepository;
    CustomerQueryRepository customerQueryRepository;
    CustomerRoleFacade roleFacade;
    CustomerFactory customerFactory;

    public CustomerFacade(CustomerRepository customerRepository, CustomerQueryRepository customerQueryRepository, CustomerRoleFacade roleFacade, CustomerFactory customerFactory) {
        this.customerRepository = customerRepository;
        this.customerQueryRepository = customerQueryRepository;
        this.roleFacade = roleFacade;
        this.customerFactory = customerFactory;
    }

    public Optional<CustomerDto> getDto(int customerId) {
        return customerQueryRepository.findDtoById(customerId);
    }

    public Customer get(int customerId) {
        return customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new NoSuchElementException("there is no customer with such id : " + customerId));
    }

    public Customer create(CustomerDto customerDto) {

        return customerRepository.save(customerFactory.from(customerDto));
    }

    public void update(Customer customer) {
        customerRepository.save(customer);
    }
    public Customer addTransfer(Customer customer, SimpleTransferQueryEntity transfer){
        customer.addTransfer(transfer);
        return customer;
    }

}
