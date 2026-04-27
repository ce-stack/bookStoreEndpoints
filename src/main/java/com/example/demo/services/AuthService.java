package com.example.demo.services;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository , RoleRepository roleRepository , JwtUtils jwtUtils) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        return jwtUtils.generateJwtToken(user.getEmail());
    }

    public String register(RegisterRequest request){
        Optional<Role> defaultRoleOpt = roleRepository.findByName("USER");
        Role defaultRole = defaultRoleOpt.orElseThrow(() -> new RuntimeException("Role not found"));
        User newUser = new User(request.getPassword(), defaultRole, defaultRole.getName(), request.getEmail(), request.getFull_name());
        userRepository.save(newUser);
        return jwtUtils.generateJwtToken(newUser.getEmail());
    }


}
