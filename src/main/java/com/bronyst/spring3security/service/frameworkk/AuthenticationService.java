package com.bronyst.spring3security.service.frameworkk;

import com.bronyst.spring3security.models.AuthenticationRequest;
import com.bronyst.spring3security.models.LoginRequest;
import com.bronyst.spring3security.models.RegisterRequest;

public interface AuthenticationService {
    AuthenticationRequest registerUser(RegisterRequest registerRequest);

    AuthenticationRequest loginUser(LoginRequest loginRequest);
}
