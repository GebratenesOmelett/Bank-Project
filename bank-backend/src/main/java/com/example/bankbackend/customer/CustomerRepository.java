package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerById(int id);

}
