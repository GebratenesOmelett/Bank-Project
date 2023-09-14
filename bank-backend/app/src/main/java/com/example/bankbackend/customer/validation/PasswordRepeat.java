package com.example.bankbackend.customer.validation;

import com.example.bankbackend.customer.dto.CustomerCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordRepeat implements ConstraintValidator<PasswordRepeatValidation, CustomerCreateDto> {
    @Override
    public void initialize(PasswordRepeatValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CustomerCreateDto customerCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        return customerCreateDto.getPassword().equals(customerCreateDto.getPasswordRepeat());
    }
}
