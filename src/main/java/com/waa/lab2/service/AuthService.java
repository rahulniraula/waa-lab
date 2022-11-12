package com.waa.lab2.service;

import com.waa.lab2.dto.incoming.LoginRequest;
import com.waa.lab2.dto.incoming.RefreshTokenRequest;
import com.waa.lab2.dto.outgoing.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
