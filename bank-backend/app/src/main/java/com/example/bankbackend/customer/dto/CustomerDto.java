package com.example.bankbackend.customer.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;

@JsonDeserialize(as = CustomerDto.DeserializationImpl.class)
public interface CustomerDto {

    static CustomerDto create(final int id,
                              final String firstName,
                              final String lastName,
                              final String email,
                              final BigDecimal funds) {
        return new DeserializationImpl(id,firstName,lastName,email,funds);
    }
    int getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    BigDecimal getFunds();


    class DeserializationImpl implements CustomerDto {
        private final int id;
        private final String firstName;
        private final String lastName;
        private final String email;
        private final BigDecimal funds;

        public DeserializationImpl(int id, String firstName, String lastName, String email, BigDecimal funds) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.funds = funds;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public String getFirstName() {
            return firstName;
        }

        @Override
        public String getLastName() {
            return lastName;
        }


        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public BigDecimal getFunds() {
            return funds;
        }


    }
}
