package com.example.aftas.seeders;

import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Level;
import com.example.aftas.repository.FishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
public class FishSeeder {
    private final FishRepository fishRepository;

    public void run(String... args) {
        if (fishRepository.count() == 0) {
            seedFishes();
        }
    }
    public void seedFishes() {
        List<Fish> fishes = List.of(
                Fish.builder()
                        .name("Tuna")
                        .averageWeight(5.0)
                        .level(Level.builder().id(1L).build())
                        .build(),
                Fish.builder()
                        .name("Salmon")
                        .averageWeight(3.0)
                        .level(Level.builder().id(2L).build())
                        .build(),
                Fish.builder()
                        .name("Sardine")
                        .averageWeight(1.0)
                        .level(Level.builder().id(3L).build())
                        .build(),
                Fish.builder()
                        .name("Herring")
                        .averageWeight(2.0)
                        .level(Level.builder().id(4L).build())
                        .build(),
                Fish.builder()
                        .name("Cod")
                        .averageWeight(4.0)
                        .level(Level.builder().id(5L).build())
                        .build(),
                Fish.builder()
                        .name("Trout")
                        .averageWeight(3.0)
                        .level(Level.builder().id(6L).build())
                        .build(),
                Fish.builder()
                        .name("Mackerel")
                        .averageWeight(2.0)
                        .level(Level.builder().id(7L).build())
                        .build(),
                Fish.builder()
                        .name("Anchovy")
                        .averageWeight(1.0)
                        .level(Level.builder().id(8L).build())
                        .build(),
                Fish.builder()
                        .name("Haddock")
                        .averageWeight(4.0)
                        .level(Level.builder().id(9L).build())
                        .build(),
                Fish.builder()
                        .name("Pike")
                        .averageWeight(3.0)
                        .level(Level.builder().id(10L).build())
                        .build()
        );

        fishRepository.saveAll(fishes);
    }
}
