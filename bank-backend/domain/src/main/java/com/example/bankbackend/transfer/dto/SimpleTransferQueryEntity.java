package com.example.bankbackend.transfer.dto;


import com.example.bankbackend.customer.dto.SimpleCustomerEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


public class SimpleTransferQueryEntity {
    public static SimpleTransferQueryEntity restore(SimpleTransferQueryEntitySnapshot snapshot){
        return new SimpleTransferQueryEntity(
                snapshot.getId(),
                snapshot.getTitle(),
                snapshot.getFunds(),
                snapshot.getReceiverId(),
                snapshot.getTransferDate(),
                snapshot.getTransferTime(),
                SimpleCustomerEntity.restore(snapshot.getCustomerId()));
    }

    private final int id;
    private final String title;
    private final BigDecimal funds;
    private final int receiverId;
    private final LocalDate transferDate;

    private final LocalTime transferTime;
    private final SimpleCustomerEntity customerId;

    private SimpleTransferQueryEntity(int id, String title, BigDecimal funds, int receiverId, LocalDate transferDate, LocalTime transferTime, SimpleCustomerEntity customerId) {
        this.id = id;
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
        this.transferDate = transferDate;
        this.transferTime = transferTime;
        this.customerId = customerId;
    }


    public SimpleTransferQueryEntitySnapshot getSnapshot(){
        return new SimpleTransferQueryEntitySnapshot(id,
                title,
                funds,
                receiverId,
                transferDate,
                transferTime,
                customerId.getSnapshot());
    }
}
