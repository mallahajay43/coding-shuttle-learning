package com.mallahajay43.coding_shuttle_learning.controllers;

import com.mallahajay43.coding_shuttle_learning.dto.StudentRequest;
import com.mallahajay43.coding_shuttle_learning.dto.StudentResponse;
import com.mallahajay43.coding_shuttle_learning.entities.Student;
import com.mallahajay43.coding_shuttle_learning.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest student){
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable("studentId") UUID studentId){
        return ResponseEntity.ok(studentService.fetchById(studentId));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(Pageable pageable){
        return ResponseEntity.ok(studentService.fetchAll(pageable));
    }
}
