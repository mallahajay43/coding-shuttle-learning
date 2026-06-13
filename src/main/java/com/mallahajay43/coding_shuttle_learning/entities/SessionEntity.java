package com.mallahajay43.coding_shuttle_learning.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String token;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;

    public SessionEntity(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
