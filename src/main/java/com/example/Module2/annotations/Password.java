package com.example.Module2.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface Password {
    String message() default "Enter Correct Password it should have one uppercase, one lowercase, one special character, and max length should be of 10 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
