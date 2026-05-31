package com.mallahajay43.coding_shuttle_learning.annotations;

import com.mallahajay43.coding_shuttle_learning.validators.EnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,  ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidator.class})
public @interface ValidEnum {
    // Points to the targeted enum class.
    Class<? extends Enum<?>> enumClass();
    String message() default "Must be a valid enum value matching the specified type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
