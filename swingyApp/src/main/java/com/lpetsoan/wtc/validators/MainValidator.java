package com.lpetsoan.wtc.validators;

import javax.validation.*;

import com.lpetsoan.wtc.validators.interfaces.StartValidator;


public class MainValidator implements ConstraintValidator<StartValidator, String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!value.equals("Gui") || !value.equals("Console")){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid view option... please enter Gui or Console " + value)
                .addPropertyNode("String")
                .addConstraintViolation();
            return false;
        }
        return true;
    }
    
}
