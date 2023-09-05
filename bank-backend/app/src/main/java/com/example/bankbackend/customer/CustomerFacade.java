package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.customer.dto.SimpleCustomerEntity;
import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public SimpleCustomerEntity toSimpleCustomerEntity(Customer customer) {
        CustomerSnapshot customerSnapshot = customer.getSnapshot();
        return new SimpleCustomerEntity(
                customerSnapshot.getId(),
                customerSnapshot.getFirstName(),
                customerSnapshot.getLastName(),
                customerSnapshot.getTransferSet().stream().
                        map(SimpleTransferQueryEntity::restore)
                        .collect(Collectors.toSet())
                        );
    }

}
