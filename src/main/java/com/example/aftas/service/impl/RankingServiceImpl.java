package com.example.aftas.service.impl;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.RankId;
import com.example.aftas.domain.Ranking;
import com.example.aftas.dto.podium.PodiumDto;
import com.example.aftas.dto.ranking.RankingDto;
import com.example.aftas.dto.ranking.RankingRequestDto;
import com.example.aftas.exceptionHandler.OperationException;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.MemberService;
import com.example.aftas.service.RankingService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;

    @Override
    public Ranking save(RankingRequestDto rankingRequestDto) {
        Member member = memberService.findMemberByNumber(rankingRequestDto.getMemberNumber());
        Competition competition = competitionService.findByCode(rankingRequestDto.getCompetitionCode());
        if (competition.getStartTime().isBefore(LocalDateTime.now().plusDays(1))) {
            throw new OperationException("Competition has already started");
        }
        if (findByCompetitionAndMember(competition, member) != null) {
            throw new IllegalArgumentException("Ranking already exists");
        }
        Ranking ranking = Ranking.builder()
                .id(RankId.builder()
                        .memberId(member.getId())
                        .competitionId(competition.getId())
                        .build())
                .member(member)
                .competition(competition)
                .score(0)
                .rank(0)
                .build();
        return rankingRepository.save(ranking);
    }

    @Override
    public Ranking findByRankId(Long id) {
        return null;
    }

    @Override
    public Ranking findByCompetitionCodeAndMemberNumber(String code, Integer number) {
        return null;
    }

    @Override
    public Ranking findByCompetitionAndMember(Competition competition, Member member) {
        return rankingRepository.findByCompetitionAndMember(competition, member);
    }

    @Override
    public List<PodiumDto> getPodiumByCompetitionId(Long competitionId) {
        List<Ranking> topRankings = rankingRepository.findTop3ByCompetitionIdOrderByScoreDesc(competitionId);
        return convertToDto(topRankings);
    }

    private List<PodiumDto> convertToDto(List<Ranking> rankings) {
        return rankings.stream()
                .map(ranking -> {
                    PodiumDto rankingDto = new PodiumDto();
                    rankingDto.setFirstName(ranking.getMember().getFirstName());
                    rankingDto.setLastName(ranking.getMember().getLastName());
                    rankingDto.setRank(ranking.getRank());
                    rankingDto.setScore(Double.valueOf(ranking.getScore()));
                    return rankingDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void changeRankingScore(Competition competition, Member member, Integer fishScore) {
        Ranking ranking = rankingRepository.findByCompetitionAndMember(competition,member);
        ranking.setScore(ranking.getScore() + fishScore);
        rankingRepository.save(ranking);
    }
}
