package com.example.demo.services;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.payload.response.ApiResponse;
import com.example.demo.payload.response.AuthResponse;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository , RoleRepository roleRepository , PasswordEncoder passwordEncoder ,JwtUtils jwtUtils) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        jwtUtils.generateJwtToken(user.getEmail());
        return new AuthResponse( jwtUtils.generateJwtToken(user.getEmail()), "user logged in"  , true );
    }

    public AuthResponse register(RegisterRequest request){
        Optional<Role> defaultRoleOpt = roleRepository.findByName("USER");
        Role defaultRole = defaultRoleOpt.orElseThrow(() -> new RuntimeException("Role not found"));
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User(hashedPassword, defaultRole, defaultRole.getName(), request.getEmail(), request.getFull_name());
        userRepository.save(newUser);
        jwtUtils.generateJwtToken(newUser.getEmail());
        return new AuthResponse(jwtUtils.generateJwtToken(newUser.getEmail()), "user created" , true);
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }


}
