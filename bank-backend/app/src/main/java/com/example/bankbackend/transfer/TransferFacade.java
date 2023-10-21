package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.dto.TransferDto;
import com.example.bankbackend.transfer.exceptions.TransferNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TransferFacade {
    TransferQueryRepository transferQueryRepository;
    TransferRepository transferRepository;
    TransferFactory transferFactory;
    CustomerFacade customerFacade;
    TransferMapper transferMapper;

    public TransferFacade(TransferQueryRepository transferQueryRepository,
                          TransferRepository transferRepository,
                          TransferFactory transferFactory,
                          CustomerFacade customerFacade,
                          TransferMapper transferMapper) {
        this.transferQueryRepository = transferQueryRepository;
        this.transferRepository = transferRepository;
        this.transferFactory = transferFactory;
        this.customerFacade = customerFacade;
        this.transferMapper = transferMapper;
    }
    @Transactional
    public TransferDto getDtoById(int id) {
        return transferQueryRepository.findDtoById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

    }

    public Set<TransferDto> getDtoByReceiverId(int id) {
        return transferQueryRepository.findDtoByReceiverId(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

    }

    public List<TransferDto> getCustomerTransfers(String email) {
         customerFacade.getByEmail(email);
        return transferQueryRepository.findCustomerTransfers(email)
                .orElseThrow(() -> new TransferNotFoundException(email))
                .stream()
                .map(transfer -> transferMapper.toTransferDto(transfer))
                .collect(Collectors.toList());
    }

    public TransferDto create(TransferCreateDto toCreate) {
        exchangeFunds(toCreate);
        return transferMapper.toTransferDto(transferRepository.save(transferFactory.from(toCreate)).getSnapshot());
    }

    public void exchangeFunds(TransferCreateDto toCreate) {
        customerFacade.updateFunds(toCreate.getReceiverId(), toCreate.getLoggedCustomerId(), toCreate.getFunds());
    }

}
