package com.example.bankbackend.customer;

import com.example.bankbackend.config.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class CustomerConfiguration {

    @Bean
    CustomerFacade customerFacade(CustomerRepository customerRepository,
                                  CustomerQueryRepository customerQueryRepository,
                                  CustomerRoleFacade customerRoleFacade,
                                  CustomerMapper customerMapper,
                                  JwtService jwtService,
                                  AuthenticationManager authenticationManager) {
        return new CustomerFacade(
                customerRepository,
                customerQueryRepository,
                customerRoleFacade,
                new CustomerFactory(customerRoleFacade,
                        new BCryptPasswordEncoder()),
                new BCryptPasswordEncoder(),
                customerMapper,
                jwtService,
                authenticationManager);
    }
    @Bean
    CustomerRoleFacade customerRoleFacade(CustomerRoleRepository customerRoleRepository) {
        return new CustomerRoleFacade(customerRoleRepository,
                new CustomerRoleFactory());

    }
}
