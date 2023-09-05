package com.example.bankbackend.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

interface SqlTransferRepository extends JpaRepository<TransferSnapshot, Integer> {
    Optional<TransferSnapshot> findTransfersById(int id);
    TransferSnapshot save(TransferSnapshot transfer);
}

interface SqlTransferQueryRepository extends TransferQueryRepository, JpaRepository<TransferSnapshot, Integer> {
}
@Repository
class TransferRepositoryImpl implements  TransferRepository{

    SqlTransferRepository repository;

    public TransferRepositoryImpl(SqlTransferRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Transfer> findTransfersById(int id) {
        return repository.findTransfersById(id).map(Transfer::restore);
    }

    @Override
    public Transfer save(Transfer transfer) {
        return Transfer.restore(repository.save(transfer.getSnapshot()));
    }
}
