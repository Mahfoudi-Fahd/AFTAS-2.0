package com.example.aftas.service;


import com.example.aftas.dto.auth.AuthenticationRequest;
import com.example.aftas.dto.auth.AuthenticationResponse;
import com.example.aftas.dto.auth.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest user);

}
