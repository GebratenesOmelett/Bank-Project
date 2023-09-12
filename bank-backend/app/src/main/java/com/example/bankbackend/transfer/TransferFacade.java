package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.dto.TransferDto;
import com.example.bankbackend.transfer.exceptions.TransferNotFoundException;

public class TransferFacade {
    TransferQueryRepository transferQueryRepository;
    TransferRepository transferRepository;
    TransferFactory transferFactory;
    CustomerFacade customerFacade;

    public TransferFacade(TransferQueryRepository transferQueryRepository,
                          TransferRepository transferRepository,
                          TransferFactory transferFactory,
                          CustomerFacade customerFacade) {
        this.transferQueryRepository = transferQueryRepository;
        this.transferRepository = transferRepository;
        this.transferFactory = transferFactory;
        this.customerFacade = customerFacade;
    }

    public TransferDto get(int id) {
        return transferQueryRepository.findDtoById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

    }

    public void create(TransferCreateDto toCreate) {
        exchangeFunds(toCreate);
        transferRepository.save(transferFactory.from(toCreate));
    }

    public void exchangeFunds(TransferCreateDto toCreate){
        customerFacade.updateFunds(toCreate);
    }
}
