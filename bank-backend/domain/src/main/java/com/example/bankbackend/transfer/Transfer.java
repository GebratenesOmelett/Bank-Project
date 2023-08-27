package com.example.bankbackend.transfer;
import com.example.bankbackend.customer.dto.SimpleCustomerQueryEntity;
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

    //todo it transfer should not see the customer, make it uni not bi;

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
