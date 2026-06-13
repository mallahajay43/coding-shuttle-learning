package com.mallahajay43.coding_shuttle_learning.service;

import com.mallahajay43.coding_shuttle_learning.entities.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
}
