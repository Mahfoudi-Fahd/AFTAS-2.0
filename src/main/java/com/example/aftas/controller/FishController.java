package com.example.aftas.controller;

import com.example.aftas.domain.Fish;
import com.example.aftas.service.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fish")
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;

    public void saveFish() {
        fishService.saveFish();
    }

    public void updateFish() {
        fishService.updateFish();
    }

    public void deleteFish() {
        fishService.deleteFish();
    }

    @GetMapping("/{fishId}")
    public Fish getFishById(Long fishId) {
        return fishService.getFishById(fishId);
    }

    @GetMapping("/all")
    public List<Fish> findAll() {
        return fishService.findAll();
    }
}
