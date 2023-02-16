package com.example.myscram.controllers;

import com.example.myscram.model.UserDto;
import com.example.myscram.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final AuthenticationService registrationService;
//    private final TokenService tokenService;


//    @PostMapping(path = "/registration",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public EntityModel<TokenDto> registration(@RequestBody UserDto user) {
//        if (registrationService.findByName(user.name()) != null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
//        }
//        else if (registrationService.findByEmail(user.email()) != null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
//        }
//        User newUser = User.builder().username(user.name()).password(user.password()).email(user.email()).build();
//        return EntityModel.of(tokenService.generateToken(registrationService.addNewUser(newUser)));
//    }

    @PostMapping(path = "/registration/validate/username",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkUsername(@RequestBody UserDto user) {
        if (registrationService.findByName(user.getName()) != null){
            log.info("User already exists with name: '{}'", user.getName());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Username is valid");
    }

    @PostMapping(path = "/registration/validate/email",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkEmail(@RequestBody UserDto user) {
        if (registrationService.findByEmail(user.getEmail()) != null){
            log.info("User already exists with name: '{}'", user.getName());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Email is valid");
    }

}
