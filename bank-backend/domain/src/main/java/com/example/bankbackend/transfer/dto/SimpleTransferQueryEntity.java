package com.example.bankbackend.transfer.dto;


import com.example.bankbackend.customer.dto.SimpleCustomerEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
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

    private int id;
    private String title;
    private BigDecimal funds;
    private int receiverId;
    private LocalDate transferDate;

    private LocalTime transferTime;
    private SimpleCustomerEntity customerId;

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
