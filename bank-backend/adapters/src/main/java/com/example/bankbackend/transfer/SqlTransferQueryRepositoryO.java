package com.example.bankbackend.transfer;

import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface SqlTransferQueryRepository extends JpaRepository<TransferSnapshot, Integer> {
    Optional<TransferDto> findDtoById(int id);
    Optional<Set<TransferDto>> findDtoByReceiverId(int id);
//    @Query(value = "SELECT ts FROM com.example.bankbackend.transfer.TransferSnapshot ts, com.example.bankbackend.customer.CustomerSnapshot cs WHERE (ts.getCustomerId = cs.id OR ts.receiverId = cs.id) and cs.email = :email")
    @Query(value = "SELECT transfer.* FROM transfer, customer WHERE (transfer.customer_id = customer.id OR transfer.receiver_id = customer.id) AND customer.email = :email ORDER BY transfer.transfer_date DESC,transfer.transfer_time DESC", nativeQuery = true)
    Optional<List<TransferSnapshot>> findCustomerTransfers(@Param("email") String email);

}
@Repository
class TransferQueryRepositoryImpl implements TransferQueryRepository{
    SqlTransferQueryRepository repository;

    public TransferQueryRepositoryImpl(SqlTransferQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TransferDto> findDtoById(int id) {
        return repository.findDtoById(id);
    }

    @Override
    public Optional<Set<TransferDto>> findDtoByReceiverId(int id) {
        return repository.findDtoByReceiverId(id);
    }

    @Override
    public Optional<List<TransferSnapshot>> findCustomerTransfers(String email) {
        return repository.findCustomerTransfers(email);
    }
}
