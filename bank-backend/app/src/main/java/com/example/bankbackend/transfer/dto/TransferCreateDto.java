package com.example.bankbackend.transfer.dto;

import java.math.BigDecimal;

public class TransferCreateDto {
    private final String title;
    private final BigDecimal funds;
    private final int loggedCustomerId;
    private final int receiverId;
    public TransferCreateDto(final String title, final BigDecimal funds, final int loggedCustomerId, final int receiverId) {
        this.title = title;
        this.funds = funds;
        this.loggedCustomerId = loggedCustomerId;
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public int getLoggedCustomerId() {
        return loggedCustomerId;
    }

    public int getReceiverId() {
        return receiverId;
    }

}
