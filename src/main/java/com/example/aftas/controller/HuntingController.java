package com.example.aftas.controller;

import com.example.aftas.domain.Hunting;
import com.example.aftas.dto.hunting.HuntingRequestDto;
import com.example.aftas.dto.hunting.HuntingResponseDto;
import com.example.aftas.dto.ranking.RankingResponseDto;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.HuntingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hunting")
@RequiredArgsConstructor
public class HuntingController {

    private final HuntingService huntingService;

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody HuntingRequestDto huntingRequestDto) {
        HuntingResponseDto rankingResponseDto = HuntingResponseDto.fromHunting(huntingService.save(huntingRequestDto));

        return ResponseMessage.created(huntingRequestDto, "Hunting created successfully");
    }

    public Hunting findById(Long id) {
        return huntingService.findById(id);
    }

    public Hunting update(Hunting hunting) {
        return huntingService.update(hunting);
    }

    public void delete(Long id) {
        huntingService.delete(id);
    }
}
