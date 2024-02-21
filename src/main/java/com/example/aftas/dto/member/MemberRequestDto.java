package com.example.aftas.dto.member;

import com.example.aftas.domain.enums.IdentityDocumentType;
import com.example.aftas.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    private Long id;



    @NotBlank(message = "firstname is required")
    private String firstName;

    @NotBlank(message = "lastname is required")
    private String lastName;

    @NotNull(message = "Date is required")
    private LocalDate accessionDate;

    @NotBlank(message = "nationality is required")
    private String nationality;

    @NotBlank(message = "identity Number is required")
    private String identityNumber;

    @NotNull(message = "identity Document Type is required")
    private IdentityDocumentType identityDocumentTypeEnum;



    public  Member toMember(){
        return  Member.builder()
                .firstName(firstName)
                .lastName(lastName)
                .accessionDate(accessionDate)
                .nationality(nationality)
                .identityNumber(identityNumber)
                .identityDocumentTypeEnum(identityDocumentTypeEnum)
                .build();

    }

}
