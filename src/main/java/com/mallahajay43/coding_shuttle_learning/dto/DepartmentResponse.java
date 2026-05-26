package com.mallahajay43.coding_shuttle_learning.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentResponse {
    private Long id;
    private String title;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "hh:mm:ss dd:MM:yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "hh:mm:ss dd:MM:yyyy")
    private LocalDateTime updatedAt;
}
