package com.mallahajay43.coding_shuttle_learning.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mallahajay43.coding_shuttle_learning.entities.AdmissionRecord;
import com.mallahajay43.coding_shuttle_learning.entities.Professor;
import com.mallahajay43.coding_shuttle_learning.entities.Subject;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class StudentResponse {
    private UUID id;
    private String name;
    private AdmissionRecord admissionRecord;
    private Set<Professor> professors;
    private Set<Subject> subjects;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime modifiedDate;
}


