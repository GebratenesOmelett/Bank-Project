package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import org.springframework.stereotype.Service;

@Service
class TransferFactory {

    CustomerFacade customerFacade;

    public TransferFactory(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    Transfer from(TransferCreateDto transferCreateDto){
        System.out.println(customerFacade.get(1));
        System.out.println(customerFacade.get(transferCreateDto.getLoggedCustomerId()));
        Transfer transfer = new Transfer(transferCreateDto.getTitle(),
                transferCreateDto.getFunds(),
                transferCreateDto.getReceiverId(),
                customerFacade.toSimpleCustomerEntity(customerFacade.get(transferCreateDto.getLoggedCustomerId())));
        System.out.println(transfer);
        return transfer;
    }
}
