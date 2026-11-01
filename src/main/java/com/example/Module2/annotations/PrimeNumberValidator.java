package com.example.Module2.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumber, Integer> {
    @Override
    public boolean isValid(Integer no, ConstraintValidatorContext constraintValidatorContext) {
        if(no == 0 || no == 1) return false;
        for(int i=2; i<=Math.sqrt(no); i++) {
            if(no % i == 0) {
                return false;
            }
        }
        return true;
    }
}
