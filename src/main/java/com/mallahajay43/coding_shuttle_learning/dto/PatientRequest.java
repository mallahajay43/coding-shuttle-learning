package com.mallahajay43.coding_shuttle_learning.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mallahajay43.coding_shuttle_learning.annotations.ValidEnum;
import com.mallahajay43.coding_shuttle_learning.utils.BloodGroup;
import com.mallahajay43.coding_shuttle_learning.utils.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Data
public class PatientRequest {
    @NotEmpty
    private String name;
    // Assigning custom enum validator to validate gender.
    @ValidEnum(enumClass = Gender.class, message = "Not a valid gender")
    private Gender gender;
    @NotEmpty
    @JsonFormat(pattern = "dd:MM:yyyy")
    private LocalDate birthDate;
    @NotEmpty
    @Email()
    private String email;
    @ValidEnum(enumClass = BloodGroup.class, message = "Not a valid blood group")
    private BloodGroup bloodGroup;
}
