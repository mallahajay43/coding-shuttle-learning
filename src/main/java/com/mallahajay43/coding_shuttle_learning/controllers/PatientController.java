package com.mallahajay43.coding_shuttle_learning.controllers;

import com.mallahajay43.coding_shuttle_learning.dto.PatientInfo;
import com.mallahajay43.coding_shuttle_learning.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<PatientInfo>> getAllPatients(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "name") String sortBy) {
        return ResponseEntity.ok(patientService.findAllPatientInfo(page, sortBy));
    }
}
