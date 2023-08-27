package com.example.bankbackend.transfer;

import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferQueryRepository extends JpaRepository<Transfer, Integer> {
    Optional<TransferDto> findDtoById(int id);
}
