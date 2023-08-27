package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.customer.dto.SimpleCustomerQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerQueryRepository extends JpaRepository<Customer,Integer> {

    Optional<CustomerDto> findDtoById(int id);

}
