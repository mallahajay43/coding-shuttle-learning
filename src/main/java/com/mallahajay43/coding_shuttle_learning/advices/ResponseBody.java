package com.mallahajay43.coding_shuttle_learning.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseBody<T> {
    private HttpStatus status;
    private T data;
    private ApiError error;
}
