package com.example.bankbackend.transfer;

import com.example.bankbackend.transfer.dto.TransferDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TransferQueryRepository {
    Optional<TransferDto> findDtoById(int id);
    Optional<Set<TransferDto>> findDtoByReceiverId(int id);
    Optional<List<TransferSnapshot>> findCustomerTransfers(String email);
}
