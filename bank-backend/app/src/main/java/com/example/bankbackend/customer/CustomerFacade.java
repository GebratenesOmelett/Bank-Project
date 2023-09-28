package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.*;
import com.example.bankbackend.customer.exceptions.CustomerEmailAlreadyExistException;
import com.example.bankbackend.customer.exceptions.CustomerNotEnoughFundsException;
import com.example.bankbackend.customer.exceptions.CustomerNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class CustomerFacade {
    CustomerRepository customerRepository;
    CustomerQueryRepository customerQueryRepository;
    CustomerRoleFacade roleFacade;
    CustomerFactory customerFactory;
    PasswordEncoder encoder;

    public CustomerFacade(CustomerRepository customerRepository, CustomerQueryRepository customerQueryRepository, CustomerRoleFacade roleFacade, CustomerFactory customerFactory, PasswordEncoder encoder) {
        this.customerRepository = customerRepository;
        this.customerQueryRepository = customerQueryRepository;
        this.roleFacade = roleFacade;
        this.customerFactory = customerFactory;
        this.encoder = encoder;
    }

    public CustomerDto getDtoById(int customerId) {
        return customerQueryRepository.findDtoById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public CustomerDto getDtoByEmail(String email){
        return customerQueryRepository.findDtoByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }

    @Transactional
    public Customer getById(int customerId) {
        return customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }
    public Customer getByEmail(String email){
        return  customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }

    public CustomerDto create(CustomerCreateDto customerCreateDto) {
        if (CustomerExists(customerCreateDto.getEmail())) {
            throw new CustomerEmailAlreadyExistException(customerCreateDto.getEmail());
        }
        return toCustomerDto(customerRepository.save(customerFactory.from(customerCreateDto)).getSnapshot());
    }

    public void update(CustomerSnapshot customerSnapshot) {
        customerRepository.save(Customer.restore(customerSnapshot));
    }


    public void addFunds(int id, BigDecimal funds) {
        CustomerSnapshot receiverCustomer = getById(id).getSnapshot();
        receiverCustomer.setFunds(receiverCustomer.getFunds().add(funds));
        update(receiverCustomer);
    }

    public void subtractFunds(int id, BigDecimal funds) {
        CustomerSnapshot loggedCustomer = getById(id).getSnapshot();
        if (loggedCustomer.getFunds().compareTo(funds) < 0) {
            throw new CustomerNotEnoughFundsException();
        }
        loggedCustomer.setFunds(loggedCustomer.getFunds().subtract(funds));
        update(loggedCustomer);
    }

    public CustomerLoginResponseDto login(CustomerLoginDto customerLoginDto) {
        if (!CustomerExists(customerLoginDto.getEmail())) {
            return new CustomerLoginResponseDto("Login Failed", false);
        }

        if (!encoder.matches(customerLoginDto.getPassword(), customerQueryRepository.findDtoByEmail(customerLoginDto.getEmail()).get().getPassword())) {
            return new CustomerLoginResponseDto("Login Failed", false);
        }

        return new CustomerLoginResponseDto("Login Succeed", true);
    }

    public SimpleCustomerEntity toSimpleCustomerEntity(Customer customer) {
        return SimpleCustomerEntity.restore(new SimpleCustomerEntitySnapshot(
                customer.getSnapshot().getId(),
                customer.getSnapshot().getFirstName(),
                customer.getSnapshot().getLastName(),
                customer.getSnapshot().getTransferSet()
        ));
    }

    public boolean CustomerExists(String email) {
        return customerQueryRepository.findDtoByEmail(email).isPresent();
    }

    public CustomerDto toCustomerDto(CustomerSnapshot customerSnapshot) {
        return CustomerDto.create(customerSnapshot.getId(),
                customerSnapshot.getFirstName(),
                customerSnapshot.getLastName(),
                customerSnapshot.getPassword(),
                customerSnapshot.getEmail(),
                customerSnapshot.getFunds());
    }



}
