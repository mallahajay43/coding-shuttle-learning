package com.mallahajay43.coding_shuttle_learning.repository;

import com.mallahajay43.coding_shuttle_learning.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatorId(UUID creatorId);
}
