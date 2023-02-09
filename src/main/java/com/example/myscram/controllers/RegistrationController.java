package com.example.myscram.controllers;

import com.example.myscram.entity.User;
import com.example.myscram.model.TokenDto;
import com.example.myscram.model.UserDto;
import com.example.myscram.service.RegistrationService;
import com.example.myscram.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
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
        LOG.info("TOKEN requested for user: '{}'", user.name());
//        return EntityModel.of(tokenService.generateToken(authentication));
//        return EntityModel.of(new TokenDto("access","refresh"));
        return EntityModel.of(tokenService.generateToken(registrationService.addNewUser(user)));
    }

//    @PostMapping(path = "/registration")
//    public String registration() {
//        return "Hello!";
//    }

    @GetMapping(path = "/registration/test")
    public String registrationTest() {
        return "Secure!";
    }

}
