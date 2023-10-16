package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.customer.dto.CustomerLoginDto;
import com.example.bankbackend.customer.dto.CustomerLoginResponseDto;
import com.example.bankbackend.customer.exceptions.CustomerEmailAlreadyExistException;
import com.example.bankbackend.customer.exceptions.CustomerNotEnoughFundsException;
import com.example.bankbackend.customer.exceptions.CustomerNotFoundException;
import com.example.bankbackend.transfer.TransferQueryRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class CustomerFacade {
    CustomerRepository customerRepository;
    CustomerQueryRepository customerQueryRepository;
    CustomerRoleFacade roleFacade;
    CustomerFactory customerFactory;
    PasswordEncoder encoder;
    CustomerMapper customerMapper;


    public CustomerFacade(CustomerRepository customerRepository,
                          CustomerQueryRepository customerQueryRepository,
                          CustomerRoleFacade roleFacade,
                          CustomerFactory customerFactory,
                          PasswordEncoder encoder,
                          CustomerMapper customerMapper,
                          TransferQueryRepository transferQueryRepository) {
        this.customerRepository = customerRepository;
        this.customerQueryRepository = customerQueryRepository;
        this.roleFacade = roleFacade;
        this.customerFactory = customerFactory;
        this.encoder = encoder;
        this.customerMapper = customerMapper;
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
        return  customerQueryRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }

    public CustomerDto create(CustomerCreateDto customerCreateDto) {
        if (customerExists(customerCreateDto.getEmail())) {
            throw new CustomerEmailAlreadyExistException(customerCreateDto.getEmail());
        }
        return customerMapper.toCustomerDto(customerRepository.save(customerFactory.from(customerCreateDto)).getSnapshot());
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

    public CustomerLoginResponseDto loginMessage(CustomerLoginDto customerLoginDto) {
        if (!customerExists(customerLoginDto.getEmail())) {
            return new CustomerLoginResponseDto("Login Failed", false);
        }
        if (!encoder.matches(customerLoginDto.getPassword(), getByEmail(customerLoginDto.getEmail()).getSnapshot().getPassword())) {
            return new CustomerLoginResponseDto("Login Failed", false);
        }

        return new CustomerLoginResponseDto("Login Succeed", true);
    }


    public boolean customerExists(String email) {
        return customerQueryRepository.findDtoByEmail(email).isPresent();
    }

}
