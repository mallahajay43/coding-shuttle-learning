package com.mallahajay43.coding_shuttle_learning.repositories;

import com.mallahajay43.coding_shuttle_learning.entities.Appointment;
import com.mallahajay43.coding_shuttle_learning.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
