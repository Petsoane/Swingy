package com.lpetsoan.wtc.validators.interfaces;

import java.lang.annotation.*;

import javax.validation.*;

import com.lpetsoan.wtc.validators.DateValidImpl;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidImpl.class)
public @interface DateValid {
    String message() default "Wierd date!";

    Class<?>[] groups() default {};
    Class<?  extends Payload>[] payload() default {};
}
