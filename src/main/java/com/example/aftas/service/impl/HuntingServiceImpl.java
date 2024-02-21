package com.example.aftas.service.impl;

import com.example.aftas.domain.*;
import com.example.aftas.dto.hunting.HuntingRequestDto;
import com.example.aftas.exceptionHandler.OperationException;
import com.example.aftas.exceptionHandler.ResourceNotFoundException;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final FishService fishService;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    private final RankingService rankingService;



    @Override
    public Hunting save(HuntingRequestDto huntingRequestDto) {


        Competition competition = competitionService.getCompetitionById(huntingRequestDto.getCompetitionId());

        if (competition.getStartTime().isAfter(LocalDateTime.now())) {
            throw new OperationException("Competition Not Started Yet");
        }
        if (competition.getEndTime().isBefore(LocalDateTime.now())) {
            throw new OperationException("Competition has ended");
        }
        Member member = memberService.getMemberById(huntingRequestDto.getMemberId());
        Fish fish = fishService.getFishById(huntingRequestDto.getFishId());

        if(fish.getAverageWeight() > huntingRequestDto.getWeightOfFish()){
            throw new IllegalArgumentException("Your fish's weight is not valid");
        }


       Hunting foundHunting = huntingRepository.findByCompetitionIdAndMemberIdAndFishId(competition.getId(), member.getId(), fish.getId());
        Integer fishScore = fish.getLevel().getPoints();


        if (foundHunting != null) {
            Hunting updatedHunting = foundHunting;
            updatedHunting.setNumberOfFish(foundHunting.getNumberOfFish() + 1);
            rankingService.changeRankingScore(competition, member,fishScore);
            return huntingRepository.save(updatedHunting);
        }else {
            Hunting hunting = Hunting.builder()
                    .fish(fish)
                    .competition(competition)
                    .member(member)
                    .numberOfFish(1)
                    .build();
            return huntingRepository.save(hunting);
        }
    }

    @Override
    public Hunting findById(Long id) {
        return null;
    }

    @Override
    public Hunting update(Hunting hunting) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
