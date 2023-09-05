package com.example.bankbackend.transfer.dto;


import com.example.bankbackend.customer.dto.SimpleCustomerEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


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
    private Date transferDate;

    private Time transferTime;
    private SimpleCustomerEntity customerId;

    public SimpleTransferQueryEntity(int id, String title, BigDecimal funds, int receiverId, Date transferDate, Time transferTime, SimpleCustomerEntity customerId) {
        this.id = id;
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
        this.transferDate = transferDate;
        this.transferTime = transferTime;
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public int getReceiverId() {
        return receiverId;
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
