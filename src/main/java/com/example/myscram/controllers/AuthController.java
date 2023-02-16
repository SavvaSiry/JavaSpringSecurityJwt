//package com.example.myscram.controllers;
//
//import com.example.myscram.entity.User;
//import com.example.myscram.model.TokenDto;
//import com.example.myscram.model.UserDto;
//import com.example.myscram.service.AuthService;
//import com.example.myscram.service.AuthenticationService;
//import com.example.myscram.service.TokenService;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class AuthController {
//
//    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
//
//    private final TokenService tokenService;
//    private final AuthService authService;
//
//
//    @PostMapping(path = "/auth",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<TokenDto> registration(@RequestBody UserDto user) {
//        User authUser = authService.authenticate(user.email(), user.password());
//        return ResponseEntity.noContent().build();
//    }
//
//}
