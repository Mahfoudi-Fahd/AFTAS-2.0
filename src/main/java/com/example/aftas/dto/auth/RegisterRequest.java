package com.example.aftas.dto.auth;

import com.example.aftas.domain.enums.IdentityDocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private IdentityDocumentType identityDocumentTypeEnum;
    private String identityDocumentNumber;
    private String nationality;
}
