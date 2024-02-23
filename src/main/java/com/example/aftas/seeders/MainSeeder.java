package com.example.aftas.seeders;

import com.example.aftas.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainSeeder implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;


    @Override
    public void run(String... args) {
        if (authorityRepository.count() == 0) {
            new AuthoritySeeder(authorityRepository).seedAuthorities();
        }

        if (roleRepository.count() == 0) {
            new RoleSeeder(roleRepository, authorityRepository).seedRoles();
        }
        if (levelRepository.count() == 0) {
            new LevelSeeder(levelRepository).seedLevels();
        }
        if (fishRepository.count() == 0) {
            new FishSeeder(fishRepository).seedFishes();
        }


    }
}
