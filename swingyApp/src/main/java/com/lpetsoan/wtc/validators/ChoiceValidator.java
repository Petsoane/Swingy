package com.lpetsoan.wtc.validators;

import javax.validation.*;
import javax.validation.constraints.Size;

import com.lpetsoan.wtc.validators.interfaces.SizeValidator;

public class ChoiceValidator implements ConstraintValidator<SizeValidator, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == -1){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Please enter a valid choice,1 or 2")
                .addPropertyNode("View Options")
                .addConstraintViolation();
            return false;
        }
        return true;
    }
}
