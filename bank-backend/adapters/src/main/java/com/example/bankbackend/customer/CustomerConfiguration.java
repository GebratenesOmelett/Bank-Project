package com.example.bankbackend.customer;

import com.example.bankbackend.transfer.TransferMapper;
import com.example.bankbackend.transfer.TransferQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class CustomerConfiguration {

    @Bean
    CustomerFacade customerFacade(CustomerRepository customerRepository,
                                  CustomerQueryRepository customerQueryRepository,
                                  CustomerRoleFacade customerRoleFacade,
                                  CustomerMapper customerMapper,
                                  TransferQueryRepository transferQueryRepository,
                                  TransferMapper transferMapper) {
        return new CustomerFacade(
                customerRepository,
                customerQueryRepository,
                customerRoleFacade,
                new CustomerFactory(customerRoleFacade,
                        new BCryptPasswordEncoder()),
                new BCryptPasswordEncoder(),
                customerMapper,
                transferQueryRepository);
    }
}
