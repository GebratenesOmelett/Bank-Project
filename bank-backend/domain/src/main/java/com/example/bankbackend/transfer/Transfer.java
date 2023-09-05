package com.example.bankbackend.transfer;
import com.example.bankbackend.customer.dto.SimpleCustomerEntity;
import lombok.ToString;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@ToString
class Transfer {
    static Transfer restore(TransferSnapshot snapshot){
        return new Transfer(
                snapshot.getId(),
                snapshot.getTitle(),
                snapshot.getFunds(),
                snapshot.getReceiverId(),
                SimpleCustomerEntity.restore(snapshot.getCustomerId()),
                snapshot.getTransferDate(),
                snapshot.getTransferTime()
        );
    }
    private int id;

    private final String title;

    private final BigDecimal funds;

    private final int receiverId;

    private final SimpleCustomerEntity customerId;

    private Date transferDate;

    private Time transferTime;

    Transfer(String title, BigDecimal funds, int receiverId, SimpleCustomerEntity customerId) {
        this.title = title;
        this.funds = funds;
        this.receiverId = receiverId;
        this.customerId = customerId;

    }

    Transfer(int id, String title, BigDecimal funds, int receiverId, SimpleCustomerEntity customerId, Date transferDate, Time transferTime) {
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
                customerId.getSnapshot()
        );
    }
}
