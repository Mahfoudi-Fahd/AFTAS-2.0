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
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Double averageWeight;

    @ManyToOne
    private Level level;

    @JsonIgnoreProperties({"fish"})
    @OneToMany(mappedBy = "fish")
    private List<Hunting> hunting;

}
