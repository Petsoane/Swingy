package com.lpetsoan.wtc.validators;

import java.lang.annotation.*;

import javax.validation.*;


import com.lpetsoan.wtc.validators.interfaces.*;

public class ConstrollerValidatorImpl implements ConstraintValidator<ControllerValidator, Integer>{
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == 0){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Wierd " + value + " The map size is wrong... maybe the player was created successfully")
                .addPropertyNode("Map")
                .addConstraintViolation();
            return false;
        }
        return true;
    }
}
