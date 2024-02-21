package com.example.aftas.controller;


import com.example.aftas.domain.Ranking;
import com.example.aftas.dto.ranking.RankingRequestDto;
import com.example.aftas.dto.ranking.RankingResponseDto;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rankings")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody RankingRequestDto rankingRequestDto){

        RankingResponseDto rankingResponseDto = RankingResponseDto.fromRanking(rankingService.save(rankingRequestDto));

return ResponseMessage.created(rankingResponseDto, "Ranking created successfully");
    }

    public Ranking findByRankId(Long id) {
        return rankingService.findByRankId(id);
    }

    public Ranking findByCompetitionCodeAndMemberNumber(String code, Integer number) {
        return rankingService.findByCompetitionCodeAndMemberNumber(code, number);
    }


}
