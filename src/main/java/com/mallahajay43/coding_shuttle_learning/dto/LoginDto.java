package com.mallahajay43.coding_shuttle_learning.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
