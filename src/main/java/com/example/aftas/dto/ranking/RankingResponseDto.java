package com.example.aftas.dto.ranking;

import com.example.aftas.domain.RankId;
import com.example.aftas.domain.Ranking;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.MemberRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankingResponseDto {


    private Integer memberNumber;
//
    private String competitionCode;

    private Integer rank;

    private Integer score;



    public static RankingResponseDto fromRanking(Ranking ranking){

        return new RankingResponseDto(
                ranking.getMember().getNumber(),
                ranking.getCompetition().getCompetitionCode(),
                ranking.getRank(),
                ranking.getScore()
        );
    }





}
