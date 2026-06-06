package com.mallahajay43.coding_shuttle_learning.advices;

import com.mallahajay43.coding_shuttle_learning.annotations.ExceptionHandlerAdvise;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResponseBodyConverter implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // Checking whether the response is coming from GlobalException Handler with custom @ExceptionHandlerAdvise
        // And assigning proper response status as well.
        if (returnType.getContainingClass().isAnnotationPresent(ExceptionHandlerAdvise.class) &&
        body instanceof ApiResponseBody<?> apiResponseBody) {
            response.setStatusCode(apiResponseBody.getError().getStatus());
        }
        if (body instanceof ApiResponseBody<?>) {
            return body;
        }
        return ApiResponseBody.builder()
                .timestamp(LocalDateTime.now())
                .data(body)
                .build();
    }
}
