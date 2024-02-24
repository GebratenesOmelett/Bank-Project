package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.CustomerFacade;
import com.example.bankbackend.customer.CustomerMapper;
import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;
import com.example.bankbackend.transfer.dto.TransferCreateDto;
import com.example.bankbackend.transfer.dto.TransferDto;
import com.example.bankbackend.transfer.exceptions.TransferNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


public class TransferFacade {
    private final TransferQueryRepository transferQueryRepository;
    private final TransferRepository transferRepository;
    private final TransferFactory transferFactory;
    private final CustomerFacade customerFacade;
    private final CustomerMapper customerMapper;
    private final TransferMapper transferMapper;

    public TransferFacade(TransferQueryRepository transferQueryRepository,
                          TransferRepository transferRepository,
                          TransferFactory transferFactory,
                          CustomerFacade customerFacade,
                          CustomerMapper customerMapper, TransferMapper transferMapper) {
        this.transferQueryRepository = transferQueryRepository;
        this.transferRepository = transferRepository;
        this.transferFactory = transferFactory;
        this.customerFacade = customerFacade;
        this.customerMapper = customerMapper;
        this.transferMapper = transferMapper;
    }

    @Transactional
    public TransferDto getDtoById(int id) {
        return transferQueryRepository.findDtoById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

    }

    public Page<TransferDto> getCustomerTransfers(String email, int page) {
        customerFacade.getByEmail(email);
        Pageable pageable = PageRequest.of(page, 5);
        SimpleCustomerEntitySnapshot customer = customerMapper.toSimpleCustomerEntity(customerFacade.getByEmail(email)).getSnapshot();
        return transferQueryRepository.findCustomerTransfers(customer, pageable)
                .map(transferMapper::toTransferDto);
    }

    public TransferDto create(TransferCreateDto toCreate) {
        exchangeFunds(toCreate);
        return transferMapper.toTransferDto(transferRepository.save(transferFactory.from(toCreate)).getSnapshot());
    }

    public void exchangeFunds(TransferCreateDto toCreate) {
        customerFacade.updateFunds(toCreate.getReceiverId(), toCreate.getLoggedCustomerId(), toCreate.getFunds());
    }

}
