package com.example.aftas.dto.auth;

import com.example.aftas.domain.Authority;
import com.example.aftas.domain.Role;
import com.example.aftas.domain.enums.AuthorityEnum;

import java.util.List;

public record RoleResponseDTO(
        String name,

        List<AuthorityEnum> authorities,
        boolean isDefault
) {
    public static RoleResponseDTO fromRole(Role role){
        return new RoleResponseDTO(
                role.getName(),
                role.getAuthorities().stream().map(Authority::getName).toList(),
                role.isDefault()
        );
    }


}
