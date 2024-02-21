package com.example.bankbackend.customer;

import java.util.Optional;
interface CustomerRepository {
    Optional<Customer> findCustomerById(int id);
    Customer save(Customer entity);




}
