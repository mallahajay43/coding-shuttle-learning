package com.mallahajay43.coding_shuttle_learning.repositories;

import com.mallahajay43.coding_shuttle_learning.entities.Insurance;
import com.mallahajay43.coding_shuttle_learning.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
}
