package com.example.aftas.service;

import com.example.aftas.domain.Fish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FishService {
    public void saveFish();
    public void updateFish();
    public void deleteFish();
    public Fish getFishById(Long fishId);
    public List<Fish> findAll();
}
