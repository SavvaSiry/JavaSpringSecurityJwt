package com.example.myscram.service;

import com.example.myscram.entity.User;
import com.example.myscram.exceptions.AppException;
import com.example.myscram.mappers.UserMapper;
import com.example.myscram.model.CredentialsDto;
import com.example.myscram.model.SignUpDto;
import com.example.myscram.model.UserDto;
import com.example.myscram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Transactional
    public UserDto authenticate(CredentialsDto credentialsDto) {
        User user = userRepository.findByEmail(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            log.debug("User {} authenticated correctly", credentialsDto.getLogin());
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto signUp(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        User savedUser = userRepository.save(user);

        log.info("Creating new user {}", userDto.getEmail());

        return userMapper.toUserDto(savedUser);
    }



    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Email not found", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public UserDto findByName(String name){
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException("Name not found", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

//     OLD CODE
    public User addNewUser(User user) {
        return userRepository.save(user);
    }


}
