package com.example.aftas.dto.member;

import com.example.aftas.domain.enums.IdentityDocumentType;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {

    private Integer number;

    private String firstName;

    private String lastName;

    private LocalDate accessionDate;

    private String nationality;

    private String identityNumber;

    private IdentityDocumentType identityDocumentTypeEnum;

    private List<Hunting> huntingList;

    private List<Ranking> rankingList;




    public static MemberResponseDto fromMember(Member member){

        return new MemberResponseDto(
                member.getNumber(),
                member.getFirstName(),
                member.getLastName(),
                member.getAccessionDate(),
                member.getNationality(),
                member.getIdentityNumber(),
                member.getIdentityDocumentTypeEnum(),
                member.getHuntingList(),
                member.getRankingList()

        );

    }

}
