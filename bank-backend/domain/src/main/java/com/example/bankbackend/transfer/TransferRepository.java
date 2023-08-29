package com.example.bankbackend.transfer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface TransferRepository {
    Optional<Transfer> findTransfersById(int id);
}
