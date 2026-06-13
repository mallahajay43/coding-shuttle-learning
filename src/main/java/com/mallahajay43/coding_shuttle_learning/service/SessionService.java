package com.mallahajay43.coding_shuttle_learning.service;

import com.mallahajay43.coding_shuttle_learning.entities.SessionEntity;
import com.mallahajay43.coding_shuttle_learning.entities.User;
import com.mallahajay43.coding_shuttle_learning.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final JwtService jwtService;

    public boolean isValidToken(String token, User user){
        return sessionRepository.findByTokenAndUser(token,user).isPresent();
    }

    public String generateToken(User user){
        String token = jwtService.accessToken(user);

        Optional<SessionEntity> savedSession = sessionRepository.findByUser(user);
        if (savedSession.isPresent()){
            SessionEntity oldSession = savedSession.get();
            oldSession.setToken(token);
            sessionRepository.save(oldSession);
            return token;
        }

        SessionEntity sessionEntity = new SessionEntity(token,user);
        sessionRepository.save(sessionEntity);
        return token;
    }
}
