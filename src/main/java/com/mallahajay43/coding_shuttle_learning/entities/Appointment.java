package com.mallahajay43.coding_shuttle_learning.entities;

import com.mallahajay43.coding_shuttle_learning.utils.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime appointmentTime;
    private String reason;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne()
    private Doctor doctor;

    @ManyToOne()
    private Patient patient;
}
