package com.mallahajay43.coding_shuttle_learning.service;

import com.mallahajay43.coding_shuttle_learning.dto.DepartmentRequest;
import com.mallahajay43.coding_shuttle_learning.dto.DepartmentResponse;
import com.mallahajay43.coding_shuttle_learning.entities.Department;
import com.mallahajay43.coding_shuttle_learning.exception.ResourceNotFoundException;
import com.mallahajay43.coding_shuttle_learning.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> modelMapper.map(department, DepartmentResponse.class))
                .toList();
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Department department = modelMapper.map(departmentRequest, Department.class);
        Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentResponse.class);
    }

    @Override
    public DepartmentResponse updateDepartmentById(Long id, DepartmentRequest departmentRequest) {
        isExist(id);
        Department existingDepartment = departmentRepository.findById(id).get();
        Department department = modelMapper.map(departmentRequest, Department.class);
        department.setId(id);
        department.setCreatedAt(existingDepartment.getCreatedAt());
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentResponse.class);
    }


    @Override
    public DepartmentResponse updatePartialDepartmentById(Long id, Map<String, Object> updates) {
        isExist(id);
        Department department = departmentRepository.findById(id).get();
        updates.forEach((field, value) -> {
            Field targetField = ReflectionUtils.getRequiredField(Department.class, field);
            targetField.setAccessible(true);
            ReflectionUtils.setField(targetField, department, value);
        });

        return modelMapper.map(departmentRepository.save(department), DepartmentResponse.class);

    }

    @Override
    public Optional<DepartmentResponse> findById(Long id) {
        return departmentRepository.findById(id)
                .map(department -> modelMapper.map(department, DepartmentResponse.class));
    }

    @Override
    public boolean deleteDepartmentById(Long id) {
        isExist(id);
        departmentRepository.deleteById(id);
        return true;
    }

    private void isExist(Long id) throws ResourceNotFoundException {
        boolean exist = departmentRepository.existsById(id);
        if (!exist) throw new ResourceNotFoundException("Department not found, id: "+id);
    }
}
