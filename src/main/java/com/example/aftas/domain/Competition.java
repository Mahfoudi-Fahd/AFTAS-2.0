package com.example.aftas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String competitionName;

    private String competitionCode;

    private LocalDate date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer numberOfParticipants;

    private  String Location;

    private Double amount;

    @JsonIgnoreProperties({"competition"})
    @OneToMany(mappedBy = "competition")
    private List<Ranking> ranking;

   @JsonIgnoreProperties({"competition"})
    @OneToMany(mappedBy = "competition")
    private List<Hunting> hunting;


}
