package com.example.bankbackend.customer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.ToString;

@ToString
@JsonDeserialize(builder = CustomerDto.Builder.class)
class CustomerDto {
    private String firstName;
    private String lastName;
    private String password;
    private String passwordRepeat;
    private String email;

    private CustomerDto(final Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        password = builder.password;
        passwordRepeat = builder.passwordRepeat;
        email = builder.email;
    }
    static public Builder builder() {
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public String getEmail() {
        return email;
    }

    @JsonPOJOBuilder
    public static class Builder{
        private String firstName;
        private String lastName;
        private String password;
        private String passwordRepeat;
        private String email;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPasswordRepeat(String passwordRepeat) {
            this.passwordRepeat = passwordRepeat;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public CustomerDto build(){
            return new CustomerDto(this);
        }
    }
}
