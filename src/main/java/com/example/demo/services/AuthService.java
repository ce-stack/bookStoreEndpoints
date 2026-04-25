package com.example.demo.services;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public AuthResponse login(LoginRequest request) {
        return new AuthResponse("dummy-token" , request.getEmail() , "amirmohammed");
    }

    public AuthResponse register(RegisterRequest $request){
        return  new AuthResponse("dummy-token" , $request.getEmail(), "amiremohammed");
    }
}
