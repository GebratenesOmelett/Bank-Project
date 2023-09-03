package com.example.bankbackend.transfer;
import com.example.bankbackend.customer.dto.SimpleCustomerEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "transfers")
class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private BigDecimal funds;

    private int receiverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private SimpleCustomerEntity customerId;

    @CreationTimestamp
    private Date transferDate;

    @CreationTimestamp
    private Time transferTime;

    protected Transfer() {
    }

    Transfer(String title, BigDecimal funds, int receiverId) {
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
    }
}
