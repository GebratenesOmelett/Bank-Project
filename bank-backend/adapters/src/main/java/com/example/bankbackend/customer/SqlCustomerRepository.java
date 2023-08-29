package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;

interface SqlCustomerRepository extends CustomerRepository, JpaRepository<Customer, Integer> {
}
