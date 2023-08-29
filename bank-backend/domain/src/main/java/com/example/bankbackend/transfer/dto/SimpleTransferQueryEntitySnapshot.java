package com.example.bankbackend.transfer.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

class SimpleTransferQueryEntitySnapshot {
    private int id;
    private String title;
    private BigDecimal funds;
    private int receiverId;
    private Date transferDate;
    private Time transferTime;

    public SimpleTransferQueryEntitySnapshot() {
    }

    SimpleTransferQueryEntitySnapshot(final int id, final String title, final BigDecimal funds, final int receiverId, final Date transferDate, final Time transferTime) {
        this.id = id;
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
        this.transferDate = transferDate;
        this.transferTime = transferTime;
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
}
