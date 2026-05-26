package com.mallahajay43.coding_shuttle_learning.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    @JsonFormat(pattern = "hh:mm:ss dd:MM:yyyy")
    private LocalDateTime timestamp;
    private ApiError error;
    private T data;

    public ApiResponse(ApiError error) {
        this.timestamp = LocalDateTime.now();
        this.error = error;
    }

    public ApiResponse(T data) {
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
}
