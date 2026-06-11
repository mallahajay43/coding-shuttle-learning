package com.mallahajay43.coding_shuttle_learning.controllers;

import com.mallahajay43.coding_shuttle_learning.dto.UserInfo;
import com.mallahajay43.coding_shuttle_learning.dto.UserRequest;
import com.mallahajay43.coding_shuttle_learning.dto.UserResponse;
import com.mallahajay43.coding_shuttle_learning.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserInfo> create(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }
}
