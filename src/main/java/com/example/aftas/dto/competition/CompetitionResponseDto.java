package com.example.aftas.dto.competition;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Ranking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionResponseDto {

        private String competitionName;

        private String competitionCode;

        private LocalDate date;

        private LocalDateTime startTime;

        private LocalDateTime endTime;

        private Integer numberOfParticipants;

        private  String Location;

        private Double amount;




      public static CompetitionResponseDto fromCompetition(Competition competition){

          return new CompetitionResponseDto(
                  competition.getCompetitionName(),
                  competition.getCompetitionCode(),
                  competition.getDate(),
                  competition.getStartTime(),
                  competition.getEndTime(),
                  competition.getNumberOfParticipants(),
                  competition.getLocation(),
                  competition.getAmount()
          );

      }


    }


