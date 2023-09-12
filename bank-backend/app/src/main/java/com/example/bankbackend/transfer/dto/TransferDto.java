package com.example.bankbackend.transfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonDeserialize(as = TransferDto.DeserializationImpl.class)
public interface TransferDto {
    static TransferDto create(final String title, final BigDecimal funds, final int receiverId, final LocalDate transferDate, final LocalTime transferTime) {
        return new DeserializationImpl(title, funds, receiverId, transferDate, transferTime);
    }

    String getTitle();

    BigDecimal getFunds();

    int getReceiverId();

    LocalDate getTransferDate();

    LocalTime getTransferTime();

    class DeserializationImpl implements TransferDto {
        private final String title;
        private final BigDecimal funds;
        private final int receiverId;
        private final LocalDate transferDate;
        private final LocalTime transferTime;

        public DeserializationImpl(String title, BigDecimal funds, int receiverId, LocalDate transferDate, LocalTime transferTime) {
            this.title = title;
            this.funds = funds;
            this.receiverId = receiverId;
            this.transferDate = transferDate;
            this.transferTime = transferTime;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public BigDecimal getFunds() {
            return funds;
        }

        @Override
        public int getReceiverId() {
            return receiverId;
        }

        @Override
        public LocalDate getTransferDate() {
            return transferDate;
        }

        @Override
        public LocalTime getTransferTime() {
            return transferTime;
        }
    }


}
