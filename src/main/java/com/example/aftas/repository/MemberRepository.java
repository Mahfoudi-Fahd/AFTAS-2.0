package com.example.aftas.repository;

import com.example.aftas.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  @Query(value =
          "SELECT * FROM member WHERE number = :searchTerm " +
                  "OR first_name LIKE %:searchTerm% OR last_name LIKE %:searchTerm%", nativeQuery = true)
  List<Member> findByNumberOrFirstNameOrLastName(@Param("searchTerm") String searchTerm);



  @Query(value = "SELECT MAX(number) FROM member", nativeQuery = true)
  Integer findMaxNumber();

  Member findByIdentityNumber(String identityNumber);

  Member findMemberByNumber(Integer number);
}
