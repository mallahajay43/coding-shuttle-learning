package com.mallahajay43.coding_shuttle_learning.service;

import com.mallahajay43.coding_shuttle_learning.dto.LoginDto;
import com.mallahajay43.coding_shuttle_learning.dto.UserRequest;
import com.mallahajay43.coding_shuttle_learning.entities.User;
import com.mallahajay43.coding_shuttle_learning.exceptions.ResourceNotFoundException;
import com.mallahajay43.coding_shuttle_learning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public User login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return user;
    }

    public User signup(UserRequest userRequest) throws BadRequestException, Exception {
        if (userRepository.existsByEmail(userRequest.getEmail())){
            throw new BadRequestException("Email already in use");
        }
        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
