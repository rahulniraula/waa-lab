package com.waa.lab2.service;

import com.waa.lab2.dto.incoming.LoginRequest;
import com.waa.lab2.dto.incoming.RefreshTokenRequest;
import com.waa.lab2.dto.outgoing.LoginResponse;
import com.waa.lab2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try{
            var result=authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        }catch (BadCredentialsException e){
            System.out.println("Bad Credentials.");

        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String accessToken=jwtUtil.generateToken(userDetails);
        final String refreshToken=jwtUtil.generateRefreshToken(loginRequest.getEmail());
        return new LoginResponse(accessToken,refreshToken);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid= jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if(isRefreshTokenValid){
            var isAccessTokenExpired=jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if(isAccessTokenExpired){
                System.out.println("Access TOken is expired");
            }else{
                System.out.println("Access token is not expired");
            }
            final String accessToken=jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse=new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            return loginResponse;
        }
        return new LoginResponse();
    }
}
