package com.example.bankbackend.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                new CustomerFactory(customerRoleFacade,
                        new BCryptPasswordEncoder()),
                new BCryptPasswordEncoder());
    }
}
