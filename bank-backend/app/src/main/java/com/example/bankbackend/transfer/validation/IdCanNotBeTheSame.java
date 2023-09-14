package com.example.bankbackend.transfer.validation;

import com.example.bankbackend.transfer.dto.TransferCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdCanNotBeTheSame implements ConstraintValidator<IdCanNotBeTheSameValidation, TransferCreateDto> {


    @Override
    public void initialize(IdCanNotBeTheSameValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TransferCreateDto transferDto, ConstraintValidatorContext context) {
        return transferDto.getLoggedCustomerId() != transferDto.getReceiverId();
    }
}
