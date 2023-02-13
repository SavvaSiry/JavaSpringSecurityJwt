package com.example.myscram.service;

import com.example.myscram.entity.User;
import com.example.myscram.model.UserDto;
import com.example.myscram.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public boolean findByEmail(String email){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id", "password", "username");
        return userRepository.exists(Example.of(User.builder().email(email).build(), exampleMatcher));
    }

    public boolean findByName(String name){
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id", "password", "email");
        return userRepository.exists(Example.of(User.builder().username(name).build(), exampleMatcher));
    }
}
