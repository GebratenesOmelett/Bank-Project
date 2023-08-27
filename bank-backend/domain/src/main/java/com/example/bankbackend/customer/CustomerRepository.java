package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerById(int id);


}
