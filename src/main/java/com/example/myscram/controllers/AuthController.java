package com.example.myscram.controllers;

import com.example.myscram.entity.User;
import com.example.myscram.model.TokenDto;
import com.example.myscram.model.UserDto;
import com.example.myscram.service.AuthService;
import com.example.myscram.service.RegistrationService;
import com.example.myscram.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    private final AuthService authService;

    public AuthController(RegistrationService registrationService, TokenService tokenService, AuthService authService) {
        this.tokenService = tokenService;
        this.authService = authService;
    }


    @PostMapping(path = "/auth",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<TokenDto> registration(@RequestBody UserDto user) {
        User authUser = authService.authenticate(user.email(), user.password());
        return EntityModel.of(tokenService.generateToken(authUser));
    }

}
