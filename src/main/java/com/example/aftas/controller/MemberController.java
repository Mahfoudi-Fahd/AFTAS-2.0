package com.example.aftas.controller;

import com.example.aftas.domain.Member;
import com.example.aftas.dto.member.MemberRequestDto;
import com.example.aftas.dto.member.MemberResponseDto;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> save(@Valid @RequestBody MemberRequestDto memberRequest)  {

    Member member = memberRequest.toMember();

      Member   membercreated = memberService.save(member);
    MemberResponseDto memberResponseDto = MemberResponseDto.fromMember(member);

    return ResponseMessage.created(memberResponseDto, "Member created successfully");
    }

    @GetMapping("/{searchString}")
    public List<Member> findByNumberOrFirstNameOrLastName(@PathVariable String searchString) {
        return memberService.findByNumberOrFirstNameOrLastName(searchString);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable Long id ,  @RequestBody MemberRequestDto memberRequestDto) {
        Member member = memberService.update(id,memberRequestDto.toMember());
    return ResponseMessage.ok(MemberResponseDto.fromMember(member), "Member updated successfully");
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id ) {
        memberService.delete(id);
    }

    @GetMapping("/all")
    public List<Member> findAll() {
        return memberService.findAll();
    }
}
