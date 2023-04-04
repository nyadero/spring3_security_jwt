package com.bronyst.spring3security.controllers;


import com.bronyst.spring3security.service.frameworkk.AuthenticationService;
import lombok.RequiredArgsConstructor;
import com.bronyst.spring3security.models.AuthenticationRequest;
import com.bronyst.spring3security.models.LoginRequest;
import com.bronyst.spring3security.models.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationRequest> registerUser(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationRequest> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.loginUser(loginRequest));
    }
}
