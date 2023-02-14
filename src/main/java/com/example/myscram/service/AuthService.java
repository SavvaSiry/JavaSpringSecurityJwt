package com.example.myscram.service;

import com.example.myscram.controllers.RegistrationController;
import com.example.myscram.entity.User;
import com.example.myscram.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String email, String password){
        try {
            User user = userRepository.getUserByEmail(email);
            if (user.getPassword().equals(password)) {
                return user;
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account with this email don't exist");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password");
    }
}
