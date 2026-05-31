package com.mallahajay43.coding_shuttle_learning.repositories;

import com.mallahajay43.coding_shuttle_learning.dto.PatientInfo;
import com.mallahajay43.coding_shuttle_learning.entities.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    // Using Data projection with the help of PatientInfo dto.
    @Query("select new com.mallahajay43.coding_shuttle_learning.dto.PatientInfo(" +
            "p.name, p.gender, p.birthDate, p.email, p.bloodGroup, p.createdAt)" +
            " from Patient p")
    List<PatientInfo> findAllPatientInfo(Pageable pageable);
}
