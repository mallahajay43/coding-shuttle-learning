package com.mallahajay43.coding_shuttle_learning.dto;

import com.mallahajay43.coding_shuttle_learning.entities.Posts;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserResponse {
    private String email;
    private String password;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    private List<Posts> posts;

    private String createdBy;
}
