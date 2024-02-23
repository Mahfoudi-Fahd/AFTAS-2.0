package com.example.aftas.dto.auth;

import com.example.aftas.domain.Member;

public record UserResponseDTO(
        String firstName,
        String lastName,
        String email,
        String role
) {
    public static UserResponseDTO fromUser(Member user){
        return new UserResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getName()
        );
    }
}
