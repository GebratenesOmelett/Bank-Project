package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import java.util.Optional;

public interface CustomerQueryRepository{

    Optional<CustomerDto> findDtoByEmail(String email);
    Optional<Customer> findCustomerByEmail(String email);
    Optional<CustomerSnapshot> findCustomerSnapshotByEmail(String email);


}
