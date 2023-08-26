package com.example.bankbackend.transfer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface TransferRepository extends JpaRepository<Transfer, Integer> {
    Optional<Transfer> findTransfersById(int id);
}
