package com.example.bankbackend.transfer;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransferFacade {
    TransferRepository transferRepository;
    public TransferFacade(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Optional<TransferDto> get(int id) {
        return transferRepository.findTransfersById(id)
                .map(Transfer::toDto);

    }

    public void create(TransferDto toCreate) {
    }
}
