package com.mallahajay43.coding_shuttle_learning.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mallahajay43.coding_shuttle_learning.utils.BloodGroup;
import com.mallahajay43.coding_shuttle_learning.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfo {
    private String name;
    private Gender gender;
    @JsonFormat(pattern = "dd:MM:yyyy")
    private LocalDate birthDate;
    private String email;
    private BloodGroup bloodGroup;
    @JsonFormat(pattern = "hh:mm:ss dd/MM/yyyy")
    private LocalDateTime createdAt;
}
