package com.example.bankbackend.transfer.dto;

import com.example.bankbackend.transfer.Validation.IdCanNotBeTheSameValidation;
import lombok.ToString;

import java.math.BigDecimal;
@ToString
@IdCanNotBeTheSameValidation
public class TransferCreateDto {
    private final String title;
    private final BigDecimal funds;
    private final int loggedCustomerId;
    private final int receiverId;
    public TransferCreateDto(String title, BigDecimal funds,  int loggedCustomerId,  int receiverId) {
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
