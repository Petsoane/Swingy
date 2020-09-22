package com.lpetsoan.wtc.validators.interfaces;

import java.lang.annotation.*;
import javax.validation.*;

import com.lpetsoan.wtc.validators.MainValidator;


@Target(ElementType.LOCAL_VARIABLE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MainValidator.class)
public @interface StartValidator {
    String message() default "Invalid value";
     
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
}
