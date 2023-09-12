package com.example.bankbackend.transfer.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdCanNotBeTheSame.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdCanNotBeTheSameValidation {
    String message() default "Id can not be the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
