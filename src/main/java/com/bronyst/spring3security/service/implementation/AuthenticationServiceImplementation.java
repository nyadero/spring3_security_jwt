package com.bronyst.spring3security.service.implementation;

import com.bronyst.spring3security.config.JWTService;
import com.bronyst.spring3security.entities.Role;
import com.bronyst.spring3security.entities.User;
import com.bronyst.spring3security.models.AuthenticationRequest;
import com.bronyst.spring3security.models.LoginRequest;
import com.bronyst.spring3security.models.RegisterRequest;
import com.bronyst.spring3security.repository.UserRepository;
import com.bronyst.spring3security.service.frameworkk.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationRequest registerUser(RegisterRequest registerRequest) {
        var user = User.builder().firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationRequest.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationRequest loginUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationRequest.builder().token(jwtToken).build();
    }
}
