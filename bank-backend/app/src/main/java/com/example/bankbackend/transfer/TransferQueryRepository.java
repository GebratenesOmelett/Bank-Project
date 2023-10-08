package com.example.bankbackend.transfer;

import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface TransferQueryRepository {
    Optional<TransferDto> findDtoById(int id);
    Optional<Set<TransferDto>> findDtoByReceiverId(int id);
    Optional<Set<TransferSnapshot>> findCustomerTransfers(String email);
}
