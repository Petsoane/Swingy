package com.lpetsoan.wtc.validators.interfaces;

import java.lang.annotation.*;
import javax.validation.*;

import com.lpetsoan.wtc.validators.ChoiceValidator;

@Target(ElementType.LOCAL_VARIABLE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChoiceValidator.class)
public @interface SizeValidator {
    String message() default "Weird date!";
     
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
}
