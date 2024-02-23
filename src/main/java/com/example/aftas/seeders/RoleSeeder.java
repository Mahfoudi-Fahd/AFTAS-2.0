package com.example.aftas.seeders;


import com.example.aftas.domain.Role;
import com.example.aftas.repository.AuthorityRepository;
import com.example.aftas.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            seedRoles();
        }
    }

    public void seedRoles() {
        List<Role> roles = List.of(
                Role.builder()
                        .name("ROLE_USER")
                        .isDefault(true)
                        .build(),
                Role.builder()
                        .name("ROLE_MANAGER")
                        .authorities(authorityRepository.findAll())
                        .isDefault(false)
                        .build(),
                Role.builder()
                        .name("ROLE_JURY")
                        .authorities(authorityRepository.findAllByName(List.of("CREATE_COMPETITION", "VIEW_COMPETI TION","DELETE_COMPETITION","UPDATE_COMPETITION")))
                        .isDefault(false)
                        .build()
        );

        roleRepository.saveAll(roles);
    }
}
