package com.mallahajay43.coding_shuttle_learning.services;

import com.mallahajay43.coding_shuttle_learning.dto.UserInfo;
import com.mallahajay43.coding_shuttle_learning.dto.UserRequest;
import com.mallahajay43.coding_shuttle_learning.dto.UserResponse;
import com.mallahajay43.coding_shuttle_learning.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//public interface UserService extends UserDetailsService {
public interface UserService {
    UserInfo createUser(UserRequest userRequest);
    UserResponse getUser(UUID userId);

    List<UserResponse> getAllUsers();
}
