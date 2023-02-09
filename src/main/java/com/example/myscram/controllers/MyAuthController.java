package com.example.myscram.controllers;

import com.example.myscram.model.TokenDto;
import com.example.myscram.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAuthController {

    private static final Logger LOG = LoggerFactory.getLogger(MyAuthController.class);

    private final TokenService tokenService;

    public MyAuthController(TokenService tokenService) {
//
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public EntityModel<TokenDto> token(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
//        return EntityModel.of(tokenService.generateToken(authentication));
        return EntityModel.of(new TokenDto("access","refresh"));
    }


}