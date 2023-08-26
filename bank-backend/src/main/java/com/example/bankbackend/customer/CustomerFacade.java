package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.role.RoleFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerFacade {
    CustomerRepository customerRepository;
    CustomerQueryRepository customerQueryRepository;
    RoleFacade roleFacade;
    CustomerFactory customerFactory;

    public CustomerFacade(CustomerRepository customerRepository, CustomerQueryRepository customerQueryRepository, RoleFacade roleFacade, CustomerFactory customerFactory) {
        this.customerRepository = customerRepository;
        this.customerQueryRepository = customerQueryRepository;
        this.roleFacade = roleFacade;
        this.customerFactory = customerFactory;
    }

    public Optional<CustomerDto> get(int customerId){
        return customerQueryRepository.findCustomerById(customerId);
    }
    public void create(CustomerDto customerDto){
        customerRepository.save(customerFactory.from(customerDto));
    }

}
