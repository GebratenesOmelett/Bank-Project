package com.example.bankbackend.transfer.dto;


import com.example.bankbackend.customer.dto.SimpleCustomerEntity;
import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


public class SimpleTransferQueryEntity {

    private int id;
    private String title;
    private BigDecimal funds;
    private int receiverId;

    private Date transferDate;

    private Time transferTime;
    private SimpleCustomerEntitySnapshot customerId;

    protected SimpleTransferQueryEntity() {
    }

    public SimpleTransferQueryEntity(String title, BigDecimal funds, int receiverId) {
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
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
}
