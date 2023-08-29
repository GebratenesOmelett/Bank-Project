package com.example.bankbackend.transfer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlTransferRepository extends TransferRepository, JpaRepository<Transfer, Integer> {
}
