package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.customer.dto.SimpleCustomerEntity;
import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;
import com.example.bankbackend.customer.exceptions.CustomerEmailAlreadyExistException;
import com.example.bankbackend.customer.exceptions.CustomerNotEnoughFundsException;
import com.example.bankbackend.customer.exceptions.CustomerNotFoundException;
import com.example.bankbackend.transfer.dto.TransferCreateDto;

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

    public CustomerDto getDto(int customerId) {
        return customerQueryRepository.findDtoById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Customer get(int customerId) {
        return customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Customer create(CustomerCreateDto customerDto) {
        if (doesCustomerExist(customerDto)) {
            throw new CustomerEmailAlreadyExistException(customerDto.getEmail());
        }
        return customerRepository.save(customerFactory.from(customerDto));
    }

    public void update(CustomerSnapshot customerSnapshot) {
        customerRepository.save(Customer.restore(customerSnapshot));
    }

    public void updateFunds(TransferCreateDto toCreate) {
        CustomerSnapshot receiverCustomer = get(toCreate.getReceiverId()).getSnapshot();
        CustomerSnapshot loggedCustomer = get(toCreate.getLoggedCustomerId()).getSnapshot();
        if (loggedCustomer.getFunds().compareTo(toCreate.getFunds()) < 0) {
            throw new CustomerNotEnoughFundsException();
        }
        receiverCustomer.setFunds(receiverCustomer.getFunds().add(toCreate.getFunds()));
        loggedCustomer.setFunds(loggedCustomer.getFunds().subtract(toCreate.getFunds()));

        update(receiverCustomer);
        update(loggedCustomer);
    }

    public SimpleCustomerEntity toSimpleCustomerEntity(Customer customer) {
        return SimpleCustomerEntity.restore(new SimpleCustomerEntitySnapshot(
                customer.getSnapshot().getId(),
                customer.getSnapshot().getFirstName(),
                customer.getSnapshot().getLastName(),
                customer.getSnapshot().getTransferSet()
        ));
    }

    public boolean doesCustomerExist(CustomerCreateDto customerDto) {
        return customerQueryRepository.findDtoByEmail((customerDto.getEmail())).isPresent();
    }

}
