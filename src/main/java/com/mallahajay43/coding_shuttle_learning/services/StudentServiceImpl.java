package com.mallahajay43.coding_shuttle_learning.services;

import com.mallahajay43.coding_shuttle_learning.dto.StudentRequest;
import com.mallahajay43.coding_shuttle_learning.dto.StudentResponse;
import com.mallahajay43.coding_shuttle_learning.entities.Student;
import com.mallahajay43.coding_shuttle_learning.exceptions.ResourceNotFoundException;
import com.mallahajay43.coding_shuttle_learning.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{
    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student student = modelMapper.map(studentRequest, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponse.class);
    }

    @Override
    public StudentResponse fetchById(UUID studentId) {
        return studentRepository.findById(studentId)
                .map(student -> modelMapper.map(student, StudentResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("Student does not exists with id: " + studentId));
    }

    @Override
    public List<StudentResponse> fetchAll(Pageable pageable) {
        logger.warn("fetchAll method is called with Pageable object");
        return studentRepository.findAll(pageable)
                .map(student -> modelMapper.map(student, StudentResponse.class))
                .toList();
    }
}
