package com.mallahajay43.coding_shuttle_learning.repository;

import com.mallahajay43.coding_shuttle_learning.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
