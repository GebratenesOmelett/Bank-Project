package com.example.bankbackend.transfer;

import org.springframework.data.jpa.repository.JpaRepository;

interface SqlTransferQueryRepository extends TransferQueryRepository, JpaRepository<Transfer, Integer> {
}
