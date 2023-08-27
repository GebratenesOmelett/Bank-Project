package com.example.bankbackend.customer.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.ToString;

@JsonDeserialize(as = CustomerDto.DeserializationImpl.class)
public interface CustomerDto {

    static CustomerDto create(final int id, final String firstName, final String lastName, final String password, final String email){
        return new DeserializationImpl(id,firstName,lastName,password,email);
    }
    int getId();
    String getFirstName();
    String getLastName();
    String getPassword();
    String getEmail();

    class DeserializationImpl implements CustomerDto{
        private int id;
        private String firstName;
        private String lastName;
        private String password;
        private String email;

        public DeserializationImpl(int id, String firstName, String lastName, String password, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.email = email;
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
    }
}
