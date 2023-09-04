package com.example.bankbackend.transfer.dto;

import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class SimpleTransferQueryEntitySnapshot {
    private int id;
    private String title;
    private BigDecimal funds;
    private int receiverId;
    private Date transferDate;
    private Time transferTime;
    private SimpleCustomerEntitySnapshot customerId;



    public void setCustomerId(SimpleCustomerEntitySnapshot customerId) {
        this.customerId = customerId;
    }

    public SimpleTransferQueryEntitySnapshot() {
    }

    SimpleTransferQueryEntitySnapshot(final int id,
                                      final String title,
                                      final BigDecimal funds,
                                      final int receiverId,
                                      final Date transferDate,
                                      final Time transferTime,
                                      final SimpleCustomerEntitySnapshot customerId) {
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
    SimpleCustomerEntitySnapshot getCustomerId() {
        return customerId;
    }

    void setId(int id) {
        this.id = id;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    void setTransferTime(Time transferTime) {
        this.transferTime = transferTime;
    }
}
