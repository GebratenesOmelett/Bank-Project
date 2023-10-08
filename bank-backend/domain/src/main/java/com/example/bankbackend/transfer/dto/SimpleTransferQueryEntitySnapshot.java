package com.example.bankbackend.transfer.dto;

import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class SimpleTransferQueryEntitySnapshot {
    private int id;
    private String title;
    private BigDecimal funds;
    private int receiverId;
    private LocalDate transferDate;
    private LocalTime transferTime;
    private SimpleCustomerEntitySnapshot customerId;


    public SimpleTransferQueryEntitySnapshot() {
    }

    SimpleTransferQueryEntitySnapshot(final int id,
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

    public int getId() {
        return id;
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

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public LocalTime getTransferTime() {
        return transferTime;
    }

    public SimpleCustomerEntitySnapshot getCustomerId() {
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

    void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    void setTransferTime(LocalTime transferTime) {
        this.transferTime = transferTime;
    }
    public void setCustomerId(SimpleCustomerEntitySnapshot customerId) {
        this.customerId = customerId;
    }
}
