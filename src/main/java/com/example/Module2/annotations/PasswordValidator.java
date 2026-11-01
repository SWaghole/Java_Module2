package com.example.Module2.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null || password.length() < 10) return false;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasCharacter = false;
        String specialCharacter = "!@#$%^&*()_+-=[]{};':\",./<>?\\|";

        for(int i=0; i<password.length(); i++) {
            char ch = password.charAt(i);

            if(Character.isLowerCase(ch)) {
                hasLower = true;
            } else if(Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if(specialCharacter.indexOf(ch) != -1) {
                hasCharacter = true;
            }
        }

        return hasUpper && hasLower && hasCharacter;
    }
}
