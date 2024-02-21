package com.example.aftas.dto.auth;

import com.example.aftas.domain.Authority;
import com.example.aftas.domain.Role;

import java.util.List;

public record RoleRequestDTO(
         String name,
         List<Authority> authorities,
         boolean isDefault
){
    public Role toRole(){
        return Role.builder()
                .name(name)
                .isDefault(isDefault)
                .authorities(authorities)
                .build();
    }
}
