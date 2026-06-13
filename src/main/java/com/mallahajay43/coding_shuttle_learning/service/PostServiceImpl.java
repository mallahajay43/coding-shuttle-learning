package com.mallahajay43.coding_shuttle_learning.service;

import com.mallahajay43.coding_shuttle_learning.entities.Post;
import com.mallahajay43.coding_shuttle_learning.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
