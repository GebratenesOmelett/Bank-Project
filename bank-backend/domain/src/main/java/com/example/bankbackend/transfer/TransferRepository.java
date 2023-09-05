package com.example.bankbackend.transfer;

import java.util.Optional;

interface TransferRepository {
    Optional<Transfer> findTransfersById(int id);
    Transfer save(Transfer transfer);
}
