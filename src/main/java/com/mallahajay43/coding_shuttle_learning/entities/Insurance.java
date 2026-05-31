package com.mallahajay43.coding_shuttle_learning.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // for testing purpose only.
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String policyNumber;
    private String provider;
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
