package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class TransferFacade {
    TransferQueryRepository transferQueryRepository;
    TransferRepository transferRepository;
    TransferFactory transferFactory;

    public TransferFacade(TransferQueryRepository transferQueryRepository, TransferRepository transferRepository, TransferFactory transferFactory) {
        this.transferQueryRepository = transferQueryRepository;
        this.transferRepository = transferRepository;
        this.transferFactory = transferFactory;
    }

    public Optional<TransferDto> get(int id) {
        return transferQueryRepository.findDtoById(id);

    }

    public void create(TransferCreateDto toCreate) {
        transferRepository.save(transferFactory.from(toCreate));
    }
}
