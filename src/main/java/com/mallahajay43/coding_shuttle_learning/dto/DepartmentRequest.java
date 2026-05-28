package com.mallahajay43.coding_shuttle_learning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DepartmentRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "title should contain only characters")
    private String name;

    @JsonProperty("isActive")
    private Boolean isActive;
}
