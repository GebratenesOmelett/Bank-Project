package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.customer.CustomerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(-102)
class TransferConfiguration {
    @Bean
    TransferFacade transferFacade(TransferQueryRepository transferQueryRepository,
                                  TransferRepository transferRepository,
                                  TransferFactory transferFactory,
                                  CustomerFacade customerFacade,
                                  CustomerMapper customerMapper,
                                  TransferMapper transferMapper) {
        return new TransferFacade(
                transferQueryRepository,
                transferRepository,
                transferFactory,
                customerFacade,
                customerMapper,
                transferMapper);
    }
}
