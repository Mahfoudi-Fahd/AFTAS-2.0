package com.example.aftas.controller;

import com.example.aftas.domain.Member;
import com.example.aftas.dto.auth.AuthenticationResponse;
import com.example.aftas.dto.auth.RegisterRequest;
import com.example.aftas.dto.member.MemberRequestDto;
import com.example.aftas.dto.member.MemberResponseDto;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.AuthenticationService;
import com.example.aftas.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('MANAGE_MEMBER')")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.createMember(request));
    }

    @GetMapping("/{searchString}")
    @PreAuthorize("hasAuthority('MANAGE_MEMBER')")
    public List<Member> findByNumberOrFirstNameOrLastName(@PathVariable String searchString) {
        return memberService.findByNumberOrFirstNameOrLastName(searchString);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('MANAGE_MEMBER')")
    public ResponseEntity<ResponseMessage> update(@PathVariable Long id ,  @RequestBody MemberRequestDto memberRequestDto) {
        Member member = memberService.update(id,memberRequestDto.toMember());
    return ResponseMessage.ok(MemberResponseDto.fromMember(member), "Member updated successfully");
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('MANAGE_MEMBER')")
    public void delete(@PathVariable Long id ) {
        memberService.delete(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('MANAGE_MEMBER')")
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @PutMapping("/enable/{id}")
    @PreAuthorize("hasAuthority('MANAGE_MEMBER')")
    public ResponseEntity<ResponseMessage> enableMemberAccount(@PathVariable Long id) {
        Member member = memberService.enableMemberAccount(id);
        return ResponseMessage.ok(MemberResponseDto.fromMember(member), "Member account enabled successfully");
    }
}
