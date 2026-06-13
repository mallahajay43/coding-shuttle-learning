package com.mallahajay43.coding_shuttle_learning.repository;

import com.mallahajay43.coding_shuttle_learning.entities.SessionEntity;
import com.mallahajay43.coding_shuttle_learning.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {
    Optional<SessionEntity> findByTokenAndUser(String token, User user);
    Optional<SessionEntity> findByUser(User user);
}
