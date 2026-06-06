package com.mallahajay43.coding_shuttle_learning.advices;

import com.mallahajay43.coding_shuttle_learning.annotations.ExceptionHandlerAdvise;
import com.mallahajay43.coding_shuttle_learning.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@ExceptionHandlerAdvise // Custom annotation to mark this class's methods for responseAdvice alteration.
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponseBody<?> handleException(Exception e){
        ApiError error = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();

        return ApiResponseBody.builder()
                .timestamp(LocalDateTime.now())
                .error(error)
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponseBody<?> handleResourceNotFoundException(ResourceNotFoundException e){
        return ApiResponseBody.builder()
                .timestamp(LocalDateTime.now())
                .error(ApiError.builder().status(HttpStatus.NOT_FOUND).message(e.getMessage()).build())
                .build();
    }
}
