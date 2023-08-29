package com.example.bankbackend.transfer;

import com.example.bankbackend.transfer.dto.TransferDto;

import java.util.Optional;

public interface TransferQueryRepository {
    Optional<TransferDto> findDtoById(int id);
}
