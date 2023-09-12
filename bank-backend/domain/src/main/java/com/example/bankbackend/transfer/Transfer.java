package com.example.bankbackend.transfer;

import com.example.bankbackend.customer.dto.SimpleCustomerEntity;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private final int id;

    private final String title;

    private final BigDecimal funds;

    private final int receiverId;

    private final SimpleCustomerEntity customerId;

    private final LocalDate transferDate;

    private final LocalTime transferTime;

    private Transfer(int id, String title, BigDecimal funds, int receiverId, SimpleCustomerEntity customerId, LocalDate transferDate, LocalTime transferTime) {
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
