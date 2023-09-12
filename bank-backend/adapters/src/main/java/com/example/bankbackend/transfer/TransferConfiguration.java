package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TransferConfiguration {
    @Bean
    TransferFacade transferFacade(TransferQueryRepository transferQueryRepository,
                                  TransferRepository transferRepository,
                                  TransferFactory transferFactory,
                                  CustomerFacade customerFacade) {
        return new TransferFacade(
                transferQueryRepository,
                transferRepository,
                transferFactory,
                customerFacade);
    }
}
