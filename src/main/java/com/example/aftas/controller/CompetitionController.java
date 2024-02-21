package com.example.aftas.controller;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.dto.competition.CompetitionRequestDto;
import com.example.aftas.dto.competition.CompetitionResponseDto;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {

  private final   CompetitionService competitionService;

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> save(@Valid @RequestBody CompetitionRequestDto competitionRequestDto) {

        Competition competition = competitionRequestDto.toCompetition();

          competitionService.save(competition);
        CompetitionResponseDto competitionResponseDto = CompetitionResponseDto.fromCompetition(competition);

        return ResponseMessage.created(     competitionResponseDto, "Competition created successfully");

    }

    @GetMapping("/{id}")
    public Competition findById(Long id) {
        return competitionService.getCompetitionById(id);
    }

    @GetMapping("/code/{code}")
    public Competition findByCode(@PathVariable String code) {
        return competitionService.findByCode(code);
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        List<Competition> competitionList = competitionService.findAll();
        List<CompetitionResponseDto> competitionResponseDtoList = new ArrayList<>();
        for (Competition competition : competitionList) {
            competitionResponseDtoList.add(CompetitionResponseDto.fromCompetition(competition));
        }
        return ResponseMessage.ok(competitionResponseDtoList, "Competitions retrieved successfully");
    }

    @GetMapping
    public ResponseEntity findAllwithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<Competition> competitionList = competitionService.findAllwithPagination(page, size);
        List<CompetitionResponseDto> competitionResponseDtoList = new ArrayList<>();
        for (Competition competition : competitionList) {
            competitionResponseDtoList.add(CompetitionResponseDto.fromCompetition(competition));
        }
        return ResponseMessage.ok(competitionResponseDtoList, "Competitions retrieved successfully");
    }

    @GetMapping("/participants/{code}")
    public ResponseEntity findParticipantsInCompetition(@PathVariable String code) {
        List<Member> memberList = competitionService.findParticipantsInCompetition(code);
        return ResponseMessage.ok(memberList, "Participants retrieved successfully");

    }

    @GetMapping("/participants/not/{code}")
    public ResponseEntity findParticipantsNotInCompetition(@PathVariable String code) {
        List<Member> memberList = competitionService.findParticipantsNotInCompetition(code);
        return ResponseMessage.ok(memberList, "Participants retrieved successfully");

    }
}
