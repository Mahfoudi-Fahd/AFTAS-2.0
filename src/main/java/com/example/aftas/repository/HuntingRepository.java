package com.example.aftas.repository;

import com.example.aftas.domain.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    Hunting findByCompetitionIdAndMemberIdAndFishId(Long competitionId, Long memberId, Long fishId);
}
