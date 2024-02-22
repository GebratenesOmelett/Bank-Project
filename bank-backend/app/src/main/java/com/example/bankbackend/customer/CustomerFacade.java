package com.example.bankbackend.customer;

import com.example.bankbackend.config.JwtService;
import com.example.bankbackend.customer.dto.CustomerCreateDto;
import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.customer.dto.CustomerLoginDto;
import com.example.bankbackend.customer.dto.CustomerAuthDto;
import com.example.bankbackend.customer.exceptions.CustomerEmailAlreadyExistException;
import com.example.bankbackend.customer.exceptions.CustomerNotEnoughFundsException;
import com.example.bankbackend.customer.exceptions.CustomerNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class CustomerFacade {
    private final CustomerRepository customerRepository;
    private final CustomerQueryRepository customerQueryRepository;
    private final CustomerRoleFacade roleFacade;
    private final CustomerFactory customerFactory;
    private final PasswordEncoder encoder;
    private final CustomerMapper customerMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public CustomerFacade(CustomerRepository customerRepository,
                          CustomerQueryRepository customerQueryRepository,
                          CustomerRoleFacade roleFacade,
                          CustomerFactory customerFactory,
                          PasswordEncoder encoder,
                          CustomerMapper customerMapper, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.customerQueryRepository = customerQueryRepository;
        this.roleFacade = roleFacade;
        this.customerFactory = customerFactory;
        this.encoder = encoder;
        this.customerMapper = customerMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public CustomerDto getDtoById(int customerId) {
        return customerQueryRepository.findDtoById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public CustomerDto getDtoByEmail(String email) {
        return customerQueryRepository.findDtoByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }


    @Transactional
    public Customer getById(int customerId) {
        return customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Transactional
    public Customer getByEmail(String email) {
        return customerQueryRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }

    public CustomerAuthDto create(CustomerCreateDto customerCreateDto) {
        if (customerExists(customerCreateDto.getEmail())) {
            throw new CustomerEmailAlreadyExistException(customerCreateDto.getEmail());
        }
        Customer customer = customerFactory.from(customerCreateDto);
        customerRepository.save(customer);
        var jwtToken = jwtService.generateToken(customer.getSnapshot());
        return CustomerAuthDto.builder()
                .id(customer.getSnapshot().getId())
                .firstName(customer.getSnapshot().getFirstName())
                .email(customer.getSnapshot().getEmail())
                .lastName(customer.getSnapshot().getLastName())
                .funds(customer.getSnapshot().getFunds())
                .token(jwtToken)
                .expiresIn(Integer.toString(JwtService.expiration))
                .build();
    }

    public void update(CustomerSnapshot customerSnapshot) {
        customerRepository.save(Customer.restore(customerSnapshot));
    }

    public void updateFunds(int idReceiver, int idAddressee, BigDecimal funds) {
        CustomerSnapshot customerReceiver = addFunds(idReceiver, funds);
        CustomerSnapshot customerAddressee = subtractFunds(idAddressee, funds);
        update(customerAddressee);
        update(customerReceiver);
    }


    public CustomerSnapshot addFunds(int id, BigDecimal funds) {
        CustomerSnapshot receiverCustomer = getById(id).getSnapshot();
        receiverCustomer.setFunds(receiverCustomer.getFunds().add(funds));
        return receiverCustomer;

    }

    public CustomerSnapshot subtractFunds(int id, BigDecimal funds) {
        CustomerSnapshot loggedCustomer = getById(id).getSnapshot();
        if (loggedCustomer.getFunds().compareTo(funds) < 0) {
            throw new CustomerNotEnoughFundsException();
        }
        loggedCustomer.setFunds(loggedCustomer.getFunds().subtract(funds));
        return loggedCustomer;
    }

//    public CustomerLoginResponseDto login(CustomerLoginDto customerLoginDto) {
//        if (!customerExists(customerLoginDto.getEmail())) {
//            return new CustomerLoginResponseDto("Login Failed", false, id, firstName, lastName, email, funds, token, expiresIn);
//        }
//        if (!encoder.matches(customerLoginDto.getPassword(), getByEmail(customerLoginDto.getEmail()).getSnapshot().getPassword())) {
//            return new CustomerLoginResponseDto("Login Failed", false, id, firstName, lastName, email, funds, token, expiresIn);
//        }
//
//        return new CustomerLoginResponseDto("Login Succeed", true, id, firstName, lastName, email, funds, token, expiresIn);
//    }

    public CustomerAuthDto login(CustomerLoginDto customerLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        customerLoginDto.getEmail(),
                        customerLoginDto.getPassword()
                )
        );
        CustomerSnapshot customer = getByEmail(customerLoginDto.getEmail()).getSnapshot();
        var jwtToken = jwtService.generateToken(customer);
        return CustomerAuthDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .email(customer.getEmail())
                .lastName(customer.getLastName())
                .funds(customer.getFunds())
                .token(jwtToken)
                .expiresIn(Integer.toString(JwtService.expiration))
                .build();
    }


    public boolean customerExists(String email) {
        return customerQueryRepository.findDtoByEmail(email).isPresent();
    }

}
