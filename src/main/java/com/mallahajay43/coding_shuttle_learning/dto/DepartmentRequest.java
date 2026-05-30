package com.mallahajay43.coding_shuttle_learning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mallahajay43.coding_shuttle_learning.annotations.CustomValidationAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DepartmentRequest {
//    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "title should contain only characters")
    @NotBlank(message = "Department title should not be empty")
    @CustomValidationAnnotation(message = "Not a valid department name", pattern = "^[a-zA-Z]+( [a-zA-Z]+)*$")
    private String name;

    @JsonProperty("isActive")
    private Boolean isActive;
}
