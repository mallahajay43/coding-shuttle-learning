package com.mallahajay43.coding_shuttle_learning.services;

import com.mallahajay43.coding_shuttle_learning.dto.UserInfo;
import com.mallahajay43.coding_shuttle_learning.dto.UserRequest;
import com.mallahajay43.coding_shuttle_learning.dto.UserResponse;
import com.mallahajay43.coding_shuttle_learning.entities.Users;
import com.mallahajay43.coding_shuttle_learning.exceptions.ResourceNotFoundException;
import com.mallahajay43.coding_shuttle_learning.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserInfo createUser(UserRequest userRequest) {
        Users user = modelMapper.map(userRequest, Users.class);
        Users createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserInfo.class);
    }

    @Override
    public UserResponse getUser(UUID userId) {
        return userRepository.findWithPostsById(userId)
                .map(user -> modelMapper.map(user, UserResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException("User does not exists with username: " + userId));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username)
//                .orElseThrow(() -> new ResourceNotFoundException("User does not exists with username: " + username));
//    }
}
