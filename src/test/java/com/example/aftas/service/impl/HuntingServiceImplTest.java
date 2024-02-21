package com.example.aftas.service.impl;

import com.example.aftas.domain.*;
import com.example.aftas.dto.hunting.HuntingRequestDto;
import com.example.aftas.exceptionHandler.OperationException;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.*;
import com.example.aftas.service.impl.HuntingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HuntingServiceImplTest {

    @Mock
    private CompetitionService competitionService;

    @InjectMocks
    private HuntingServiceImpl huntingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveWhenCompetitionNotStarted() {
        // Mock data
        HuntingRequestDto huntingRequestDto = new HuntingRequestDto();
        huntingRequestDto.setCompetitionId(1L);
        huntingRequestDto.setMemberId(2L);
        huntingRequestDto.setFishId(3L);
        huntingRequestDto.setWeightOfFish(10);

        Competition competition = new Competition();
        competition.setStartTime(LocalDateTime.now().plusHours(1));

        when(competitionService.getCompetitionById(1L)).thenReturn(competition);

        assertThrows(OperationException.class, () -> huntingService.save(huntingRequestDto));
    }

}
