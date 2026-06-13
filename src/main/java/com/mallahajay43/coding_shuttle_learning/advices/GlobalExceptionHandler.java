package com.mallahajay43.coding_shuttle_learning.advices;

import com.mallahajay43.coding_shuttle_learning.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseBody<?>> handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiError error = ApiError.builder()
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(
                ResponseBody.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .error(error)
                        .build(),
                HttpStatus.NOT_FOUND
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBody<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value",
                        (existingValue, newValue) -> existingValue));

        ApiError error = ApiError.builder()
                .error("Invalid data provided")
                .errors(errors)
                .build();

        return new ResponseEntity<>(
                ResponseBody.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .error(error)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBody<?>> handleException(Exception ex){
        ApiError error = ApiError.builder()
                .error(ex.getMessage())
                .build();
        return new ResponseEntity<>(
                ResponseBody.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .error(error)
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseBody<?>> handleAuthenticationException(AuthenticationException ex){
        ApiError error = ApiError.builder()
                .error(ex.getLocalizedMessage())
                .build();

        return new ResponseEntity<>(
                ResponseBody.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .error(error)
                        .build(),
                HttpStatus.UNAUTHORIZED
        );

    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ResponseBody<?>> handleJwtException(JwtException ex){
        ApiError error = ApiError.builder()
                .error(ex.getLocalizedMessage())
                .build();

        return new ResponseEntity<>(
                ResponseBody.builder()
                        .status(HttpStatus.UNAUTHORIZED)
                        .error(error)
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }
}
