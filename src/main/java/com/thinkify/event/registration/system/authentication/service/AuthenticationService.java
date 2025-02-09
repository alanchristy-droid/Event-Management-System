package com.thinkify.event.registration.system.authentication.service;

import com.thinkify.event.registration.system.authentication.authenticationDTO.AuthenticationRequest;
import com.thinkify.event.registration.system.authentication.authenticationDTO.AuthenticationResponse;
import com.thinkify.event.registration.system.authentication.authenticationDTO.RegistrationRequest;
import com.thinkify.event.registration.system.authentication.config.service.JWTService;
import com.thinkify.event.registration.system.authentication.user.repository.UserRepository;
import com.thinkify.event.registration.system.authentication.user.userDTO.Role;
import com.thinkify.event.registration.system.authentication.user.userDTO.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) {

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
