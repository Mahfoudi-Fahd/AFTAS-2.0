package com.example.aftas.service;

import com.example.aftas.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    public Member save( Member member);

    public List<Member> findByNumberOrFirstNameOrLastName(String searchString);

    public Member update(Long id ,Member member);

    public void delete(Long id);

    public List<Member> findAll();
    Member findMemberByNumber(Integer number);

    Member getMemberById(Long memberId);
}
