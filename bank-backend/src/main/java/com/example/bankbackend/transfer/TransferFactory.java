package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
class TransferFactory {

    CustomerFacade customerFacade;

    public TransferFactory(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    Transfer from(TransferDto transferDto){
        return new Transfer(transferDto.getTitle(),
                transferDto.getFunds(),
                transferDto.getReceiverId(),
                customerFacade.get(transferDto.getLoggedCustomerId()));

    }
}
