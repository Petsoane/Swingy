package com.lpetsoan.wtc.validators;

import java.time.LocalDate;

import javax.validation.*;

import com.lpetsoan.wtc.validators.interfaces.DateValid;

public class DateValidImpl implements  ConstraintValidator<DateValid, LocalDate>{
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {

        LocalDate lastCentury = LocalDate.of(1877, 6,4 );

        if (date.isBefore(lastCentury) || date.isAfter(LocalDate.now())){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Wierd " + date + " The phone is wrong...")
                .addPropertyNode("map")
                .addConstraintViolation();
            return false;
        }
        
        return true;
    }
    
}
