package com.example.bankbackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;

interface SqlCustomerQueryRepository extends CustomerQueryRepository, JpaRepository<Customer, Integer> {
}
