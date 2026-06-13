package com.mallahajay43.coding_shuttle_learning.service;

import com.mallahajay43.coding_shuttle_learning.entities.User;

import java.util.UUID;

public interface JwtService {
    String accessToken(User user);
    UUID getUserId(String token);
}
