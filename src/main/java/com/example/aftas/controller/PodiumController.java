package com.example.aftas.controller;

import com.example.aftas.dto.podium.PodiumDto;
import com.example.aftas.dto.ranking.RankingDto;
import com.example.aftas.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PodiumController {
    private final RankingService rankingService;

    // Constructor injection of RankingService

    @GetMapping("/competition/{competitionId}/podium")
    public ResponseEntity<List<PodiumDto>> getPodiumForCompetition(@PathVariable Long competitionId) {
        List<PodiumDto> podium = rankingService.getPodiumByCompetitionId(competitionId);
        return ResponseEntity.ok(podium);
    }
}
