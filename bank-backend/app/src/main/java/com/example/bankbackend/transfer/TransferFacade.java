package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferFacade {
    TransferQueryRepository transferQueryRepository;
    CustomerFacade customerFacade;

    public TransferFacade(TransferQueryRepository transferQueryRepository, CustomerFacade customerFacade) {
        this.transferQueryRepository = transferQueryRepository;
        this.customerFacade = customerFacade;
    }

    public Optional<TransferDto> get(int id) {
        return transferQueryRepository.findDtoById(id);

    }

    public void create(TransferCreateDto toCreate) {
        customerFacade.update(customerFacade.addTransfer(customerFacade.get(toCreate.getLoggedCustomerId()),
                toCreate.toSimpleEntity()));
    }
}
