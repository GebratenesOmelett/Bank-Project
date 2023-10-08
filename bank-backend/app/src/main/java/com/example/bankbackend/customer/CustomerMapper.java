package com.example.bankbackend.customer;

import com.example.bankbackend.customer.dto.CustomerDto;
import com.example.bankbackend.customer.dto.SimpleCustomerEntity;
import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;
import com.example.bankbackend.transfer.TransferMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {


    TransferMapper transferMapper;

    public CustomerMapper(TransferMapper transferMapper) {
        this.transferMapper = transferMapper;
    }

    public SimpleCustomerEntity toSimpleCustomerEntity(Customer customer) {
        return SimpleCustomerEntity.restore(new SimpleCustomerEntitySnapshot(
                customer.getSnapshot().getId(),
                customer.getSnapshot().getFirstName(),
                customer.getSnapshot().getLastName(),
                customer.getSnapshot().getTransferSet()
        ));
    }

    public CustomerDto toCustomerDto(CustomerSnapshot customerSnapshot) {
        return CustomerDto.create(customerSnapshot.getId(),
                customerSnapshot.getFirstName(),
                customerSnapshot.getLastName(),
                customerSnapshot.getEmail(),
                customerSnapshot.getFunds());
    }
}
