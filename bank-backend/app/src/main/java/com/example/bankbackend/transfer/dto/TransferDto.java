package com.example.bankbackend.transfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@JsonDeserialize(as = TransferDto.DeserializationImpl.class)
public interface TransferDto {
    static TransferDto create(final String title, final BigDecimal funds, final int receiverId, final Date transferDate, final Time transferTime) {
        return new DeserializationImpl(title, funds, receiverId, transferDate, transferTime);
    }

    String getTitle();

    BigDecimal getFunds();

    int getReceiverId();

    Date getTransferDate();

    Time getTransferTime();

    class DeserializationImpl implements TransferDto {
        private final String title;
        private final BigDecimal funds;
        private final int receiverId;
        private Date transferDate;
        private Time transferTime;

        public DeserializationImpl(String title, BigDecimal funds, int receiverId, Date transferDate, Time transferTime) {
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
        public Date getTransferDate() {
            return transferDate;
        }

        @Override
        public Time getTransferTime() {
            return transferTime;
        }
    }


}
