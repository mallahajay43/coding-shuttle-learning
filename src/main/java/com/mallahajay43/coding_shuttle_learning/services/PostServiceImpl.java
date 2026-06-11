package com.mallahajay43.coding_shuttle_learning.services;

import com.mallahajay43.coding_shuttle_learning.dto.PostRequest;
import com.mallahajay43.coding_shuttle_learning.dto.PostResponse;
import com.mallahajay43.coding_shuttle_learning.entities.Posts;
import com.mallahajay43.coding_shuttle_learning.entities.Users;
import com.mallahajay43.coding_shuttle_learning.exceptions.ResourceNotFoundException;
import com.mallahajay43.coding_shuttle_learning.repositories.PostRepository;
import com.mallahajay43.coding_shuttle_learning.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostResponse createPost(UUID creatorId, PostRequest postRequest) {
        // Getting user entity.
        Users creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exists with id: " + creatorId));
        Posts post = modelMapper.map(postRequest, Posts.class);
        post.setCreator(creator);

        Posts createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostResponse.class);
    }

    @Override
    public PostResponse updatePost(UUID creatorId, Long postId, PostRequest postRequest) {
        Posts post = postRepository.findByIdAndCreator_Id(postId, creatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Post does not exists with post id and user id: " + postId + ", " + creatorId));
        // Updating existing post value with update request.
        modelMapper.map(postRequest, post);
        Posts updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostResponse.class);
    }

    @Override
    public boolean deletePostByCreatorId(UUID creatorId) {
        return false;
    }

    @Override
    public void deletePostByPostId(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Unable to delete, Post does not exists with post id " + postId);
        }
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostResponse> getPostByCreator(UUID creatorId) {
        return postRepository.findByCreatorId(creatorId).stream()
                .map(post -> modelMapper.map(post, PostResponse.class))
                .toList();
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> modelMapper.map(post, PostResponse.class))
                .toList();
    }

    @Override
    public PostResponse getPostById(Long postId) {
        return postRepository.findById(postId)
                .map(post -> modelMapper.map(post, PostResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("Post does not exists with post id " + postId));
    }

}
