package com.example.demo.services;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository , RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public AuthResponse login(LoginRequest request) {
        return new AuthResponse("dummy-token" , request.getEmail() , "amirmohammed");
    }

    public AuthResponse register(RegisterRequest $request){
        return  new AuthResponse("dummy-token" , $request.getEmail(), "amiremohammed");
    }
}
