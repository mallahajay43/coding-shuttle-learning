package com.mallahajay43.coding_shuttle_learning.services;

import com.mallahajay43.coding_shuttle_learning.dto.PostRequest;
import com.mallahajay43.coding_shuttle_learning.dto.PostResponse;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponse createPost(UUID creator, PostRequest postRequest);
    PostResponse updatePost(UUID creator, Long postId, PostRequest postRequest);
    boolean deletePostByCreatorId(UUID creatorId);
    void deletePostByPostId(Long postId);

    List<PostResponse> getPostByCreator(UUID creator);
    List<PostResponse> getAllPosts();
    PostResponse getPostById(Long postId);
}
