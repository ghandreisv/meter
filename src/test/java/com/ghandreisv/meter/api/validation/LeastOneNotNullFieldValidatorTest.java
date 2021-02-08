package com.ghandreisv.meter.api.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LeastOneNotNullFieldValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void violations_when_all_fields_null() {
        ValidatedObject validatedObject = new ValidatedObject();
        Set<ConstraintViolation<ValidatedObject>> constraintViolations = validator.validate(validatedObject);
        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void success_when_one_fields_not_null() {
        ValidatedObject validatedObject = new ValidatedObject("val1", null);
        Set<ConstraintViolation<ValidatedObject>> constraintViolations = validator.validate(validatedObject);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void success_when_all_fields_not_null() {
        ValidatedObject validatedObject = new ValidatedObject("val1", "val2");
        Set<ConstraintViolation<ValidatedObject>> constraintViolations = validator.validate(validatedObject);
        assertTrue(constraintViolations.isEmpty());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @LeastOneNotNullField(fields = {"field1", "field2"})
    @Getter
    public static class ValidatedObject {
        private String field1;
        private String field2;
    }

}