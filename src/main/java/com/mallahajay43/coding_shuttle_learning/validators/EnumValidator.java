package com.mallahajay43.coding_shuttle_learning.validators;


import com.mallahajay43.coding_shuttle_learning.annotations.ValidEnum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<ValidEnum,String> {
    private Set<String> acceptedValues;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null || s.isEmpty()) return false;

        return acceptedValues.contains(s);
    }
}
