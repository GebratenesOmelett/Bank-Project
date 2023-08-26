package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.SimpleCustomerQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerById(int id);


}
