package com.mallahajay43.coding_shuttle_learning.services;

import com.mallahajay43.coding_shuttle_learning.dto.PatientInfo;
import com.mallahajay43.coding_shuttle_learning.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;

    private final int PAGE_SIZE = 10;

    @Override
    public List<PatientInfo> findAllPatientInfo(int page, String sortBy) {
        Pageable pageable= PageRequest.of(page,PAGE_SIZE, Sort.by(Sort.Direction.DESC,  sortBy));
        return patientRepository.findAllPatientInfo(pageable);
    }
}
