package com.example.aftas.dto.competition;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Ranking;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionRequestDto {

    @NotBlank(message = "competitionName is required")
    private String competitionName;

    @NotNull (message = "date is required")
    @DateTimeFormat(pattern = "yy-MM-dd")
    @Future(message = "date must be in the future")
    private LocalDate date;

    @NotNull (message = "startTime is required")
    @Future(message = "startTime must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @NotNull (message = "endTime is required")
    @Future(message = "endTime must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    @NotNull (message = "numberOfParticipants is required")
    private Integer numberOfParticipants;

    @NotBlank(message = "Location is required")
    private  String Location;

    @NotNull (message = "amount is required")
    private Double amount;


    public Competition toCompetition(){
        return  Competition.builder()
                .competitionName(competitionName)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .numberOfParticipants(numberOfParticipants)
                .Location(Location)
                .amount(amount)
                .build();
    }


}
