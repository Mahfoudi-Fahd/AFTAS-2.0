package com.example.aftas.service;

import com.example.aftas.domain.Member;
import com.example.aftas.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    List<Member> getAll();

    Optional<Member> getById(Long id);

    Member assignRole(Long id, Role role);

    Member update(Member user, Long id);

    void delete(Long id);

    Member loadAuthUser();

}
