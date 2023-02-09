package com.example.myscram.service;

import com.example.myscram.entity.User;
import com.example.myscram.model.UserDto;
import com.example.myscram.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(UserDto user) {
        return userRepository.save(User.builder().username(user.name()).password(user.password()).email(user.email()).build());
    }
}
