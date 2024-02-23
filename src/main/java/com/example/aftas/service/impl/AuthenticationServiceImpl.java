package com.example.aftas.service.impl;

import com.example.aftas.domain.Member;
import com.example.aftas.dto.auth.AuthenticationRequest;
import com.example.aftas.dto.auth.AuthenticationResponse;
import com.example.aftas.dto.auth.RegisterRequest;
import com.example.aftas.repository.UserRepository;
import com.example.aftas.security.JwtService;
import com.example.aftas.service.AuthenticationService;
import com.example.aftas.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Integer number = userRepository.findMaxNumber();
        if (number == null) {
            number = 1;
        } else {
            number++;
        }
        var user = Member.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleService.findDefaultRole().orElse(null))
                .identityDocumentTypeEnum(request.getIdentityDocumentTypeEnum())
                .identityNumber(request.getIdentityDocumentNumber())
                .nationality(request.getNationality())
                .accessionDate(LocalDate.now())
                .number(number)
                .enabled(false)
                .build();


        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder().token(jwtToken).refreshToken(refreshToken).build();
    }

    @Override
    public AuthenticationResponse createMember(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Integer number = userRepository.findMaxNumber();
        if (number == null) {
            number = 1;
        } else {
            number++;
        }
        var user = Member.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode("password"))
                .role(roleService.findDefaultRole().orElse(null))
                .identityDocumentTypeEnum(request.getIdentityDocumentTypeEnum())
                .identityNumber(request.getIdentityDocumentNumber())
                .number(number)
                .accessionDate(LocalDate.now())
                .enabled(true)
                .build();


        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}