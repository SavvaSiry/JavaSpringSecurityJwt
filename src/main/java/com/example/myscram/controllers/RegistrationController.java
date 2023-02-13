package com.example.myscram.controllers;

import com.example.myscram.entity.User;
import com.example.myscram.model.TokenDto;
import com.example.myscram.model.UserDto;
import com.example.myscram.service.RegistrationService;
import com.example.myscram.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ControllerAdvice
public class RegistrationController {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationService registrationService;
    public final TokenService tokenService;

    public RegistrationController(RegistrationService registrationService, TokenService tokenService) {
        this.registrationService = registrationService;
        this.tokenService = tokenService;
    }

    @PostMapping(path = "/registration",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<TokenDto> registration(@RequestBody UserDto user) {
        if (registrationService.findByName(user.name())){
            LOG.info("User already exists with name: '{}'", user.name());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        else if (registrationService.findByEmail(user.email())) {
            LOG.info("User already exists with email: '{}'", user.email());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        User newUser = User.builder().username(user.name()).password(user.password()).email(user.email()).build();
        return EntityModel.of(tokenService.generateToken(registrationService.addNewUser(newUser)));
    }

    @PostMapping(path = "/registration/validate/username",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkUsername(@RequestBody UserDto user) {
        if (registrationService.findByName(user.name())){
            LOG.info("User already exists with name: '{}'", user.name());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Username is valid");
    }

    @PostMapping(path = "/registration/validate/email",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkEmail(@RequestBody UserDto user) {
        if (registrationService.findByEmail(user.email())){
            LOG.info("User already exists with name: '{}'", user.name());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Email is valid");
    }

}
