package com.mallahajay43.coding_shuttle_learning.services;

import com.mallahajay43.coding_shuttle_learning.dto.PatientInfo;

import java.util.List;

public interface PatientService {
    List<PatientInfo> findAllPatientInfo(int page, String sortBy);
}
