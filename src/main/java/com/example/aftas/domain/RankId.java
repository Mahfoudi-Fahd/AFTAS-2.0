package com.example.aftas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rank_id")
public class RankId implements Serializable {
    private Long memberId;

    private Long competitionId;

}