package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;
import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TransferQueryRepository {
    Optional<TransferDto> findDtoById(int id);
    Optional<Set<TransferDto>> findDtoByReceiverId(int id);
    Page<TransferSnapshot> findCustomerTransfers(SimpleCustomerEntitySnapshot customer, Pageable pageable);
}
