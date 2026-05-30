package com.mallahajay43.coding_shuttle_learning.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<CustomValidationAnnotation,String> {
    private String pattern;

    @Override
    public void initialize(CustomValidationAnnotation constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s==null || s.isEmpty()) return false;

        if (pattern==null || pattern.isEmpty()) return true;

        return s.matches(pattern);
    }
}
