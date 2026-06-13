package com.mallahajay43.coding_shuttle_learning.controllers;

import com.mallahajay43.coding_shuttle_learning.dto.LoginDto;
import com.mallahajay43.coding_shuttle_learning.dto.AuthDto;
import com.mallahajay43.coding_shuttle_learning.dto.UserRequest;
import com.mallahajay43.coding_shuttle_learning.dto.UserResponse;
import com.mallahajay43.coding_shuttle_learning.entities.User;
import com.mallahajay43.coding_shuttle_learning.service.AuthService;
import com.mallahajay43.coding_shuttle_learning.service.JwtService;
import com.mallahajay43.coding_shuttle_learning.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final SessionService sessionService;

    @PostMapping("/signup")
    public ResponseEntity<AuthDto> signup(@RequestBody UserRequest userRequest) throws Exception {
        User user = authService.signup(userRequest);
        String token = sessionService.generateToken(user);

        // Setting authentication token in security context holder.
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, null));

        AuthDto authDto = AuthDto.builder()
                .user(modelMapper.map(user, UserResponse.class))
                .token(token)
                .build();

        return new ResponseEntity<>(authDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@Valid @RequestBody LoginDto loginDto) throws Exception {
        User user = authService.login(loginDto);
        String token = sessionService.generateToken(user);

        AuthDto authDto = AuthDto.builder()
                .user(modelMapper.map(user, UserResponse.class))
                .token(token)
                .build();

        return new ResponseEntity<>(authDto, HttpStatus.OK);
    }
}
