package com.example.bankbackend.customer.dto;


import com.example.bankbackend.transfer.dto.SimpleTransferQueryEntity;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@ToString
public class SimpleCustomerEntity {
    public static SimpleCustomerEntity restore(SimpleCustomerEntitySnapshot snapshot){
        return new SimpleCustomerEntity(snapshot.getId(),
                snapshot.getFirstName(),
                snapshot.getLastName(),
                snapshot.getTransferSet().stream().map(SimpleTransferQueryEntity::restore).collect(Collectors.toSet()));
    }

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Set<SimpleTransferQueryEntity> transferSet;

    public SimpleCustomerEntity(int id, String firstName, String lastName, Set<SimpleTransferQueryEntity> transferSet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.transferSet = transferSet;
    }
    public SimpleCustomerEntitySnapshot getSnapshot(){
        return new SimpleCustomerEntitySnapshot(id,
                firstName,
                lastName,
                transferSet.stream()
                        .map(SimpleTransferQueryEntity::getSnapshot).collect(Collectors.toSet()));
    }

}
