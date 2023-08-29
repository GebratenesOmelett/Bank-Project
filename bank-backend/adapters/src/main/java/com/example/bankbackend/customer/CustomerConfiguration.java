package com.example.bankbackend.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CustomerConfiguration {

    @Bean
    CustomerFacade customerFacade(CustomerRepository customerRepository,
                                  CustomerQueryRepository customerQueryRepository,
                                  CustomerRoleFacade customerRoleFacade) {
        return new CustomerFacade(
                customerRepository,
                customerQueryRepository,
                customerRoleFacade,
                new CustomerFactory(customerRoleFacade));
    }
}
