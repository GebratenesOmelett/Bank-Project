package com.example.bankbackend.customer.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;

@JsonDeserialize(as = CustomerDto.DeserializationImpl.class)
public interface CustomerDto {

    static CustomerDto create(final int id,
                              final String firstName,
                              final String lastName,
                              final String password,
                              final String email,
                              final BigDecimal funds) {
        return new DeserializationImpl(id,firstName,lastName,password,email,funds);
    }
    int getId();
    String getFirstName();
    String getLastName();
    String getPassword();
    String getEmail();
    BigDecimal getFunds();

    class DeserializationImpl implements CustomerDto{
        private final int id;
        private final String firstName;
        private final String lastName;
        private final String password;
        private final String email;
        private final BigDecimal funds;

        public DeserializationImpl(int id, String firstName, String lastName, String password, String email, BigDecimal funds) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
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
        public String getPassword() {
            return password;
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
