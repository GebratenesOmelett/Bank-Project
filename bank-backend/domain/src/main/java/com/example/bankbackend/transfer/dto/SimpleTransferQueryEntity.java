package com.example.bankbackend.transfer.dto;


import com.example.bankbackend.customer.dto.SimpleCustomerEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "transfers")
public class SimpleTransferQueryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private BigDecimal funds;
    @CreationTimestamp
    private Date transferDate;
    @CreationTimestamp
    private Time transferTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private SimpleCustomerEntity customerId;

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
