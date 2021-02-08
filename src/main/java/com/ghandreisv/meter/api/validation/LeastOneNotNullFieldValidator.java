package com.ghandreisv.meter.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.stream.Stream;

public class LeastOneNotNullFieldValidator implements ConstraintValidator<LeastOneNotNullField, Object> {

    private String[] fields;

    @Override
    public void initialize(LeastOneNotNullField leastOneNotNullField) {
        this.fields = leastOneNotNullField.fields();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return Stream.of(fields)
                .map(field -> ReflectionUtil.getFieldValue(o, field))
                .anyMatch(Objects::nonNull);
    }
}
