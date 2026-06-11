package com.mallahajay43.coding_shuttle_learning.repositories;

import com.mallahajay43.coding_shuttle_learning.dto.UserInfo;
import com.mallahajay43.coding_shuttle_learning.entities.Posts;
import com.mallahajay43.coding_shuttle_learning.entities.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);

    // Overrides standard findById to eagerly fetch posts in 1 single database hit
    @EntityGraph(attributePaths = "com.mallahajay43.coding_shuttle_learning.entities.Posts")
    Optional<Users> findWithPostsById(UUID id);

    @Query("SELECT new com.mallahajay43.coding_shuttle_learning.dto.UserInfo(u.id, u.email, u.createdDate, u.modifiedDate) FROM Users u WHERE u.id = :id")
    Optional<UserInfo> findUserInfoById(@Param("id") UUID id);
}
