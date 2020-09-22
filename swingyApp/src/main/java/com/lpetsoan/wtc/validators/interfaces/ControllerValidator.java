package com.lpetsoan.wtc.validators.interfaces;

import java.lang.annotation.*;

import javax.validation.*;

import com.lpetsoan.wtc.validators.ConstrollerValidatorImpl;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConstrollerValidatorImpl.class)
public @interface ControllerValidator {
    String message() default "Wierd date!";

    Class<?>[] groups() default {};
    Class<?  extends Payload>[] payload() default {};
}
