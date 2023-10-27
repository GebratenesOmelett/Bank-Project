package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.customer.CustomerMapper;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
class TransferFactory {

    CustomerFacade customerFacade;

    CustomerMapper customerMapper;

    public TransferFactory(CustomerFacade customerFacade, CustomerMapper customerMapper) {
        this.customerFacade = customerFacade;
        this.customerMapper = customerMapper;
    }

    Transfer from(TransferCreateDto transferCreateDto) {

        return Transfer.restore(TransferSnapshot.builder()
                .title(transferCreateDto.getTitle())
                .funds(transferCreateDto.getFunds())
                .receiverId(transferCreateDto.getReceiverId())
                .customerId(customerMapper.toSimpleCustomerEntity(customerFacade.getById(transferCreateDto.getLoggedCustomerId())).getSnapshot())
                .transferDate(LocalDate.now())
                .transferTime(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute()))
                .build());
    }
}
