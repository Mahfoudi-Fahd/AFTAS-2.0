package com.example.aftas.domain;

import com.example.aftas.domain.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private String firstName;

    private String lastName;

    private LocalDate accessionDate;

    private String nationality;

    private String identityNumber;

    private String email;

    private String password;

    private Integer counter;

    private Boolean enabled;

    private IdentityDocumentType identityDocumentTypeEnum;

    @ManyToOne
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Hunting> huntingList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Ranking> rankingList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().name())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
