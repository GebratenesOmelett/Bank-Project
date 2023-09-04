package com.example.bankbackend.transfer;
import com.example.bankbackend.customer.dto.SimpleCustomerEntitySnapshot;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


class Transfer {
    static Transfer restore(TransferSnapshot snapshot){
        return new Transfer(
                snapshot.getId(),
                snapshot.getTitle(),
                snapshot.getFunds(),
                snapshot.getReceiverId(),
                snapshot.getCustomerId(),
                snapshot.getTransferDate(),
                snapshot.getTransferTime()
        );
    }
    private int id;

    private String title;

    private BigDecimal funds;

    private int receiverId;

    private SimpleCustomerEntitySnapshot customerId;

    private Date transferDate;

    private Time transferTime;

    Transfer(String title, BigDecimal funds, int receiverId) {
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
    }

    Transfer(int id, String title, BigDecimal funds, int receiverId, SimpleCustomerEntitySnapshot customerId, Date transferDate, Time transferTime) {
        this.id = id;
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
        this.customerId = customerId;
        this.transferDate = transferDate;
        this.transferTime = transferTime;
    }
    TransferSnapshot getSnapshot(){
        return new TransferSnapshot(
                id,
                title,
                funds,
                receiverId,
                transferDate,
                transferTime,
                customerId
        );
    }
}
