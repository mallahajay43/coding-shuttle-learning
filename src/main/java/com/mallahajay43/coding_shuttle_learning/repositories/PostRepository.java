package com.mallahajay43.coding_shuttle_learning.repositories;

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
public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByCreatorId(UUID creator);

    @Query(
            "SELECT p FROM Posts p WHERE p.id = :postId AND p.creator.id = :creatorId"
    )
    Optional<Posts> findByIdAndCreator_Id(@Param("postId") Long id, @Param("creatorId") UUID creatorId);
}
