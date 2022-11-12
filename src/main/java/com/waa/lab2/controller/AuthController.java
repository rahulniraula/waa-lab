package com.waa.lab2.controller;

import com.waa.lab2.dto.incoming.LoginRequest;
import com.waa.lab2.dto.incoming.RefreshTokenRequest;
import com.waa.lab2.dto.outgoing.LoginResponse;
import com.waa.lab2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthController {
    @Autowired
    private  AuthService authService;

    @PostMapping("/login")
    public LoginResponse loginResponse(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }
}
