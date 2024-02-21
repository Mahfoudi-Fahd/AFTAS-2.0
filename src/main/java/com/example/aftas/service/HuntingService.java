package com.example.aftas.service;

import com.example.aftas.domain.Hunting;
import com.example.aftas.dto.hunting.HuntingRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface HuntingService {
    public Hunting save(HuntingRequestDto huntingRequestDto);
    public Hunting findById(Long id);
    public Hunting update(Hunting hunting);
    public void delete(Long id);
}
