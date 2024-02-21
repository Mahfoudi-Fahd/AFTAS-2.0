package com.example.aftas.dto.hunting;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Member;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Setter
@Component
@AllArgsConstructor
@Builder
public class HuntingRequestDto {

    @NotNull(message = "Member id is required")
    Long memberId;
    @NotNull(message = "Fish id is required")
    Long fishId;
    @NotNull(message = "Competition id is required")
    Long competitionId;

    @NotNull(message = "Number of fish is required")
    @Min(value = 1, message = "Number of fish must be greater than 0")
    double weightOfFish;


//    public Hunting toHunting (){
//        return Hunting.builder()
//                .competition(Competition.builder().id(competitionId).build())
//                .member(Member.builder().id(memberId).build())
//                .fish(Fish.builder().id(fishId).averageWeight(weightOfFish).build())
//                .build();
//
//    }


}
