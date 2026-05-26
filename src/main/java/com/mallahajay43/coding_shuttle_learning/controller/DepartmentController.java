package com.mallahajay43.coding_shuttle_learning.controller;

import com.mallahajay43.coding_shuttle_learning.dto.DepartmentRequest;
import com.mallahajay43.coding_shuttle_learning.dto.DepartmentResponse;
import com.mallahajay43.coding_shuttle_learning.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
        return ResponseEntity.ok().body(departmentService.findAll());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.findById(departmentId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody @Valid DepartmentRequest departmentRequest) {
        return ResponseEntity.ok().body(departmentService.createDepartment(departmentRequest));
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> updateDepartmentById(@PathVariable("departmentId") Long id, @RequestBody @Valid DepartmentRequest departmentRequest) {
        return ResponseEntity.ok().body(departmentService.updateDepartmentById(id, departmentRequest));
    }

    @PatchMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> updatePartialDepartmentById(@PathVariable("departmentId") Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok().body(departmentService.updatePartialDepartmentById(id, updates));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long departmentId) {
        if (departmentService.deleteDepartmentById(departmentId)) {
            return ResponseEntity.ok("Department deleted successfully, id: " + departmentId);
        }
        return ResponseEntity.notFound().build();
    }
}
