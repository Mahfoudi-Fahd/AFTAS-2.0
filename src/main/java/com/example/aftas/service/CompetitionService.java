package com.example.aftas.service;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetitionService {

    public Competition save(Competition competition);
    public List<Competition> findAll();

    List<Competition> findAllwithPagination(int page, int size);

    Competition findByCode(String code);
    Competition getCompetitionById(Long competitionId);

    List<Member> findParticipantsInCompetition(String code);
    List<Member> findParticipantsNotInCompetition(String code);
}
