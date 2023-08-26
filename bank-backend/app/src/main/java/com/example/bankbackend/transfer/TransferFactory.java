package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.stereotype.Service;

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
