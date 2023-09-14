package com.example.bankbackend.customer.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordRepeat.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordRepeatValidation {
    String message() default "Passwords are not the same";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};
}
