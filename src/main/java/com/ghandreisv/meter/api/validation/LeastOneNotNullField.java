package com.ghandreisv.meter.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LeastOneNotNullFieldValidator.class)
public @interface LeastOneNotNullField {

    String message() default "At least one non null field of {fields} required";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fields();

}