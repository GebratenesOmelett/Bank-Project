package com.example.bankbackend.transfer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.math.BigDecimal;
@JsonDeserialize(builder = TransferDto.Builder.class)
class TransferDto {

    private String title;
    private BigDecimal funds;
    private int loggedCustomerId;
    private int receiverId;
    private TransferDto(final TransferDto.Builder builder) {
        title = builder.title;
        funds = builder.funds;
        loggedCustomerId = builder.loggedCustomerId;
        receiverId = builder.receiverId;
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

    static public TransferDto.Builder builder() {
        return new TransferDto.Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{
        private String title;
        private BigDecimal funds;
        private int loggedCustomerId;
        private int receiverId;

        public TransferDto.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public TransferDto.Builder withFunds(BigDecimal funds) {
            this.funds = funds;
            return this;
        }

        public TransferDto.Builder withLoggedCustomerId(int loggedCustomerId) {
            this.loggedCustomerId = loggedCustomerId;
            return this;
        }

        public TransferDto.Builder withReceiverId(int receiverId) {
            this.receiverId = receiverId;
            return this;
        }
        public TransferDto build(){
            return new TransferDto(this);
        }
    }
}
