package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;
import com.example.bankbackend.transfer.dto.TransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface SqlTransferQueryRepository extends JpaRepository<TransferSnapshot, Integer> {
    Optional<TransferDto> findDtoById(int id);
    Page<TransferSnapshot> findAllByCustomerIdOrReceiverIdOrderByTransferDateDescTransferTimeDesc(SimpleCustomerEntitySnapshot customer,Integer id, Pageable pageable);
    List<TransferDto> findAllByCustomerIdOrderByTransferDateDescTransferTimeDesc(SimpleCustomerEntitySnapshot customer);
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
    public Page<TransferSnapshot> findCustomerTransfers(SimpleCustomerEntitySnapshot customer, Pageable pageable) {
        return repository.findAllByCustomerIdOrReceiverIdOrderByTransferDateDescTransferTimeDesc(customer,customer.getId(), pageable);
    }
    @Override
    public List<TransferDto> findAddressBookByEmail(SimpleCustomerEntitySnapshot customer){
        return repository.findAllByCustomerIdOrderByTransferDateDescTransferTimeDesc(customer);
    }

}
