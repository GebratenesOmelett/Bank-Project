package com.example.bankbackend.transfer;
import com.example.bankbackend.customer.query.SimpleCustomerQueryDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private BigDecimal amountOfMoney;

    private int customerIdTo;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private SimpleCustomerQueryDto customerIdFrom;

    @CreationTimestamp
    private Date transferDate;

    @CreationTimestamp
    private Time transferTime;

    protected Transfer() {
    }

    public Transfer(int id, String title, BigDecimal amountOfMoney, int customerIdTo, SimpleCustomerQueryDto customerIdFrom, Date transferDate, Time transferTime) {
        this.id = id;
        this.title = title;
        this.amountOfMoney = amountOfMoney;
        this.customerIdTo = customerIdTo;
        this.customerIdFrom = customerIdFrom;
        this.transferDate = transferDate;
        this.transferTime = transferTime;
    }
}
