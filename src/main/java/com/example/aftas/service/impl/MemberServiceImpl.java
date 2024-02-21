package com.example.aftas.service.impl;

import com.example.aftas.domain.Member;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;




    @Override
    public Member save(@Valid Member member) {

        //generate member number
        Integer number = memberRepository.findMaxNumber();
        if (number == null) {
            number = 1;
        } else {
            number++;
        }
        member.setNumber(number);

        //check if member already exist
        Member member1 = memberRepository.findByIdentityNumber( member.getIdentityNumber());

        if (member1 != null) {
            throw new RuntimeException("Member already exist");
        }
        return memberRepository.save(member);
    }

    @Override
    public List<Member> findByNumberOrFirstNameOrLastName(String searchString) {
      return  memberRepository.findByNumberOrFirstNameOrLastName(searchString);
    }

    @Override
    public Member update(Long id, Member member) {
        return memberRepository.save(member);    }



    @Override
    public void delete(Long id) {
         memberRepository.deleteById(id);

    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findMemberByNumber(Integer number) {
        return memberRepository.findMemberByNumber(number);
    }

    @Override
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member with id " + memberId + " not found"));
    }
}
