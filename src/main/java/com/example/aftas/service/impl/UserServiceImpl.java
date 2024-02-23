package com.example.aftas.service.impl;

import com.example.aftas.domain.Member;
import com.example.aftas.domain.Role;
import com.example.aftas.repository.UserRepository;
import com.example.aftas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<Member> getAll() {
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("VIEW_USERS"))return userRepository.findAll();
        return null;
    }

    @Override
    public Optional<Member> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Member assignRole(Long id, Role role) {
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("ASSIGN_ROLE_TO_USER")){
            Member user = getById(id).orElse(null);
            if (user != null && role != null){
                user.setRole(role);
                return userRepository.save(user);
            }
            return null;
        }return null;
    }

    @Override
    public Member update(Member user, Long id) {
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("UPDATE_USERS") || SecurityContextHolder.getContext().getAuthentication().getName().equals(user.getEmail())){
            Member existingUser = getById(id).orElse(null);
            if (existingUser != null){
                existingUser.setEmail(user.getEmail());
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                return userRepository.save(user);
            }
            return null;
        }return null;
    }

    @Override
    public void delete(Long id) {
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("DELETE_USERS") || SecurityContextHolder.getContext().getAuthentication().getName().equals(getById(id).get().getEmail()))getById(id).ifPresent(userRepository::delete);
    }

    @Override
    public Member loadAuthUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
    }
}
