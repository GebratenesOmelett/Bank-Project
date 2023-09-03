package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.dto.SimpleCustomerEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class TransferSnapshot {
    private int id;
    private String title;
    private BigDecimal funds;
    private int receiverId;
    private Date transferDate;
    private Time transferTime;
    private SimpleCustomerEntity customerId;

    public TransferSnapshot() {
    }

    TransferSnapshot(final int id,
                     final String title,
                     final BigDecimal funds,
                     final int receiverId,
                     final Date transferDate,
                     final Time transferTime,
                     final SimpleCustomerEntity customerId) {
        this.id = id;
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
        this.transferDate = transferDate;
        this.transferTime = transferTime;
        this.customerId = customerId;
    }

    int getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    BigDecimal getFunds() {
        return funds;
    }

    int getReceiverId() {
        return receiverId;
    }

    Date getTransferDate() {
        return transferDate;
    }

    Time getTransferTime() {
        return transferTime;
    }

    SimpleCustomerEntity getCustomerId() {
        return customerId;
    }
}
