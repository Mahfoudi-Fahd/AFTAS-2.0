package com.example.aftas.service;

import com.example.aftas.domain.Role;
import com.example.aftas.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    List<User> getAll();

    Optional<User> getById(Long id);

    User assignRole(Long id, Role role);

    User update(User user, Long id);

    void delete(Long id);

    User loadAuthUser();

}
