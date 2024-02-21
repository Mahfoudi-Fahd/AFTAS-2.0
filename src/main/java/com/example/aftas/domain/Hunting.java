package com.example.aftas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer numberOfFish;

    @ManyToOne
    private Member member;

   @ManyToOne
    private Fish fish;

    @JsonIgnoreProperties({"hunting"})
    @ManyToOne
    private Competition competition;
}
