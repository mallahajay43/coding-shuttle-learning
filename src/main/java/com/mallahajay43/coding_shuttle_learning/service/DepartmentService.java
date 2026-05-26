package com.mallahajay43.coding_shuttle_learning.service;

import com.mallahajay43.coding_shuttle_learning.dto.DepartmentRequest;
import com.mallahajay43.coding_shuttle_learning.dto.DepartmentResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    List<DepartmentResponse> findAll();
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse updateDepartmentById(Long id, DepartmentRequest departmentRequest);

    boolean deleteDepartmentById(Long id);

    DepartmentResponse updatePartialDepartmentById(Long id, Map<String, Object> updates);

    Optional<DepartmentResponse> findById(Long id);
}
