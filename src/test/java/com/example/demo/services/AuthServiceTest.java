package com.example.demo.services;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.payload.response.AuthResponse;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    RoleRepository roleRepository;

    @Mock
    JwtUtils jwtUtils;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_new_user_when_email_does_not_exist() {
        //arrange

        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));


        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFull_name("amir test");
        registerRequest.setEmail("amirunitTest@gmail.com");
        registerRequest.setPassword("password123");

        when(userRepository.existsByEmail("amirunitTest@gmail.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encode password");
        when(userRepository.save(any(User.class ))).thenAnswer(invocation -> invocation.getArgument(0));

        //act
        when(jwtUtils.generateJwtToken("amirunitTest@gmail.com")).thenReturn("fake-jwt-token");
        AuthResponse authResponse = authService.register(registerRequest);

        //Assert
        assertNotNull(authResponse);

        //verify
        verify(userRepository).existsByEmail("amirunitTest@gmail.com");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void login_user_when_email_exist() {

        //arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("amirtestunit@gmail.com");
        loginRequest.setPassword("password123");

        User user = new User();
        user.setEmail("amirtestunit@gmail.com");
        user.setPassword("encoded-password");

        when(userRepository.findByEmail("amirtestunit@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123" , "encoded-password")).thenReturn(true);
        when(jwtUtils.generateJwtToken("amirtestunit@gmail.com")).thenReturn("fake-jwt-token");

        //act
        AuthResponse authResponse = authService.login(loginRequest);

        //assert
        assertNotNull(authResponse);
        assertEquals("fake-jwt-token" , authResponse.getToken());
        assertEquals("user logged in" , authResponse.getMessage());
        assertEquals(true , authResponse.getSuccess());

        //verify
        verify(userRepository).findByEmail("amirtestunit@gmail.com");
        verify(passwordEncoder).matches("password123" , "encoded-password");
        verify(jwtUtils).generateJwtToken("amirtestunit@gmail.com");


    }

}
