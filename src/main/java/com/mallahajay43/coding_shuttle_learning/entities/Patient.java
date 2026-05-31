package com.mallahajay43.coding_shuttle_learning.entities;

import com.mallahajay43.coding_shuttle_learning.utils.BloodGroup;
import com.mallahajay43.coding_shuttle_learning.utils.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor // Only for testing purpose, avoid using in Entity class.
@Table(
        name = "patient",
        catalog = "spring_data_tutorial", // A higher-level container that typically represents the actual physical database instance or name.
        schema = "people",  // A logical grouping of database objects (like tables, views, and stored procedures) inside a database.
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"})
        },
        indexes = {
                @Index(name = "idx_email", columnList = "email")
        }
)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthDate;
    private String email;

    @Enumerated(EnumType.STRING)  // Tells Hibernate to store enums string value, not there index like 1, 2 etc.
    private BloodGroup bloodGroup;

    @CreationTimestamp // Hibernate automatically handles.
    @Column(updatable = false) // Restrict from further modification.
    private LocalDateTime createdAt;

    // Setting CascadeType to all, updates, add, deletes child automatically when parent affected.
    /**
     *  ALL
     *      Propagates all entity state transitions listed below.
     *  PERSIST
     *      Saves child entities automatically when the parent is saved (EntityManager.persist()).
     *  MERGE
     *      Updates child entities in the database when the parent is updated (EntityManager.merge()).
     *  REMOVE
     *      Deletes child entities automatically when the parent is deleted (EntityManager.remove()).
     *  REFRESH
     *      Reloads child entities from the database when the parent is reloaded.
     *  DETACH
     *      Removes child entities from the persistent context when the parent is detached.
     */
    @OneToOne(cascade = CascadeType.ALL) // Cascade states: Transient, Persist, Merge etc.
    private Insurance insurance;
}
