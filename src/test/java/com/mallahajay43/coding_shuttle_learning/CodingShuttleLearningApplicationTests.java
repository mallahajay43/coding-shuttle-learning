package com.mallahajay43.coding_shuttle_learning;

import com.mallahajay43.coding_shuttle_learning.entities.Insurance;
import com.mallahajay43.coding_shuttle_learning.entities.Patient;
import com.mallahajay43.coding_shuttle_learning.repositories.InsuranceRepository;
import com.mallahajay43.coding_shuttle_learning.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CodingShuttleLearningApplicationTests {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;

	@Test
    void contextLoads() {
//        Insurance insurance = Insurance.builder()
//                .policyNumber("POLICY2026001")
//                .provider("TATA AIG")
//                .validUntil(LocalDate.of(2026, 7, 31))
//                .build();
//
//        Long id = 1L;
//        addInsurance(insurance, id);
    }

//    @Test
    void addInsurance(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(insurance);
        patientRepository.save(patient);
    }

    @Test
    @Transactional
    void deleteInsurance(){
        Patient patient = patientRepository.findById(1L).orElseThrow();
        patient.setInsurance(null);
        patientRepository.save(patient);
        System.out.println(patient);
    }

}
