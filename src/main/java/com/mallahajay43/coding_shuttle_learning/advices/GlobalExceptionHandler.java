package com.mallahajay43.coding_shuttle_learning.advices;

import com.mallahajay43.coding_shuttle_learning.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiError handleResourceNotFoundException(ResourceNotFoundException ex){
        return  ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .error(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value",
                        (existingValue, newValue) -> existingValue));

        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("Invalid data provided")
                .errors(errors)
                .build();
    }
}
