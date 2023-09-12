package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
class TransferFactory {

    CustomerFacade customerFacade;

    public TransferFactory(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    Transfer from(TransferCreateDto transferCreateDto) {

        return Transfer.restore(TransferSnapshot.builder()
                .title(transferCreateDto.getTitle())
                .funds(transferCreateDto.getFunds())
                .receiverId(transferCreateDto.getReceiverId())
                .customerId(customerFacade.toSimpleCustomerEntity(customerFacade.get(transferCreateDto.getLoggedCustomerId())).getSnapshot())
                .transferDate(LocalDate.now())
                .transferTime(LocalTime.now())
                .build());
    }
}
