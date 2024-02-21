package com.example.aftas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Ranking {

    @EmbeddedId
    private RankId id;

    @ManyToOne
    @MapsId("memberId")
    private Member member;

    @JsonIgnoreProperties({"ranking","hunting"})
    @ManyToOne
    @MapsId("competitionId")
    private Competition competition;

    private Integer rank;

    private Integer score;

}
