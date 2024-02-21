package com.example.aftas.dto.hunting;

import com.example.aftas.domain.Hunting;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class HuntingResponseDto {

    Long memberId;
    Long fishId;
    Long competitionId;

    double weightOfFish;


    public static HuntingResponseDto fromHunting(Hunting hunting){

        return new HuntingResponseDto(
                hunting.getMember().getId(),
                hunting.getFish().getId(),
                hunting.getCompetition().getId(),
                hunting.getFish().getAverageWeight()
        );
    }
}
