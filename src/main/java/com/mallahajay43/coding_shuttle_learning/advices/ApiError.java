package com.mallahajay43.coding_shuttle_learning.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String error;
    private Map<String, String> errors;
}
