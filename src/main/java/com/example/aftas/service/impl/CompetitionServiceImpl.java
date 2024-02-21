package com.example.aftas.service.impl;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class CompetitionServiceImpl implements CompetitionService {

   private final CompetitionRepository competitionRepository;
   private final MemberService memberService;

    @Override
    public Competition save(Competition competition) {

        // Check if a competition with the same date already exists
        if ( competitionExistsWithSameDate(competition.getDate())) {

            throw new IllegalArgumentException("A competition with the same date already exists");
        }

        String code = generateCode(competition.getLocation(), competition.getDate());
        competition.setCompetitionCode(code);

        return competitionRepository.save(competition);
    }




    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    @Override
    public List<Competition> findAllwithPagination(int page, int size) {
        return competitionRepository.findAll(Pageable.ofSize(size).withPage(page)).getContent();
    }

    @Override
    public Competition findByCode(String code) {
        return competitionRepository.findByCompetitionCode(code);
    }

    @Override
    public Competition getCompetitionById(Long competitionId) {
        return competitionRepository.findById(competitionId).orElseThrow(() -> new IllegalArgumentException("Competition with id " + competitionId + " not found"));
    }

    @Override
    public List<Member> findParticipantsInCompetition(String code) {

        Competition byCompetitionCode = competitionRepository.findByCompetitionCode(code);
        if (competitionRepository.findByCompetitionCode(code) == null) {
            throw new IllegalArgumentException("Competition with code " + code + " not found");
        }
        List<Member> members = byCompetitionCode.getRanking().stream().map(ranking -> ranking.getMember()).collect(Collectors.toList());

        List<Member> allMembers = memberService.findAll();

        List<Member> participants = new ArrayList<>();

        return members.stream().filter(member -> allMembers.contains(member)).collect(Collectors.toList());
    }

    @Override
    public List<Member> findParticipantsNotInCompetition(String code) {
        return memberService.findAll().stream().filter(member -> !findParticipantsInCompetition(code).contains(member)).collect(Collectors.toList());
    }


    public static String generateCode(String location, LocalDate date) {
        String locationCode = location.substring(0, Math.min(location.length(), 3)).toLowerCase();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = date.format(dateFormatter);

        return locationCode + "-" + formattedDate;
    }


    private boolean competitionExistsWithSameDate(LocalDate date) {
        List<Competition> competitionsWithSameDate = competitionRepository.findByDate(date);

        return !competitionsWithSameDate.isEmpty();
    }
}
