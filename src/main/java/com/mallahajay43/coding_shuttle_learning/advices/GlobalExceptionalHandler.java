package com.mallahajay43.coding_shuttle_learning.advices;

import com.mallahajay43.coding_shuttle_learning.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGlobalException(Exception e) {
        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return new ApiResponse<>(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<?> resourceNotFoundException(ResourceNotFoundException e) {
        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ApiResponse<>(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> fieldErrors = e.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder()
                .message("Input Validation Failed")
                .status(HttpStatus.BAD_REQUEST)
                .subErrors(fieldErrors)
                .build();

        return new ApiResponse<>(apiError);
    }
}
