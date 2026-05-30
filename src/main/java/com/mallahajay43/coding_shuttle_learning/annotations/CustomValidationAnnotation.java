package com.mallahajay43.coding_shuttle_learning.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomValidator.class)
public @interface CustomValidationAnnotation {
    String pattern() default "";
    String message() default "title should contain only characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
