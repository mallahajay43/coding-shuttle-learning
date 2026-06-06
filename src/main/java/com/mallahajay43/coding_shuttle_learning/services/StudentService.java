package com.mallahajay43.coding_shuttle_learning.services;

import com.mallahajay43.coding_shuttle_learning.dto.StudentRequest;
import com.mallahajay43.coding_shuttle_learning.dto.StudentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    StudentResponse addStudent(StudentRequest student);

    StudentResponse fetchById(UUID studentId);

    List<StudentResponse> fetchAll(Pageable pageable);
}
