package com.mallahajay43.coding_shuttle_learning.service;

import com.mallahajay43.coding_shuttle_learning.dto.UserRequest;
import com.mallahajay43.coding_shuttle_learning.entities.User;
import com.mallahajay43.coding_shuttle_learning.exceptions.ResourceNotFoundException;
import com.mallahajay43.coding_shuttle_learning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetching user by email address.
        return userRepository.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found with email: " + username)
        );
    }

    public Optional<User> findUserById(UUID userId) throws Exception {
        return userRepository.findById(userId);
    }
}
