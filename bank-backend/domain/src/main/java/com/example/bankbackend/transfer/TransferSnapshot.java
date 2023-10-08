package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
@ToString
@Builder
class TransferSnapshot {


    private int id;
    private String title;
    private BigDecimal funds;
    private int receiverId;
    private LocalDate transferDate;
    private LocalTime transferTime;
    private SimpleCustomerEntitySnapshot customerId;

    public TransferSnapshot() {
    }

    TransferSnapshot(final int id,
                     final String title,
                     final BigDecimal funds,
                     final int receiverId,
                     final LocalDate transferDate,
                     final LocalTime transferTime,
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

    LocalDate getTransferDate() {
        return transferDate;
    }

    LocalTime getTransferTime() {
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

    void setCustomerId(SimpleCustomerEntitySnapshot customerId) {
        this.customerId = customerId;
    }

    void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    void setTransferTime(LocalTime transferTime) {
        this.transferTime = transferTime;
    }
}
