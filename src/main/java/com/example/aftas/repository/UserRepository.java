package com.example.aftas.repository;

import com.example.aftas.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "SELECT MAX(number) FROM member", nativeQuery = true)
    Integer findMaxNumber();}
