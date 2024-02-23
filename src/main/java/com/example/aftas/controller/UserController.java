package com.example.aftas.controller;

import com.example.aftas.domain.Member;
import com.example.aftas.domain.Role;
import com.example.aftas.dto.auth.RoleRequestDTO;
import com.example.aftas.dto.auth.UserResponseDTO;
import com.example.aftas.service.RoleService;
import com.example.aftas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @PutMapping("/assign_role/{id}")
    @PreAuthorize("hasAuthority('MANAGE_MEMBER')")
    public ResponseEntity<UserResponseDTO> assignRole(@RequestBody RoleRequestDTO request, @PathVariable Long id){
        Role role = roleService.getByName(request.name()).orElse(null);
        Member user = userService.assignRole(id, role);
        if (user == null && role == null) return ResponseEntity.badRequest().build();
        else {
            assert user != null;
            return new ResponseEntity<>(UserResponseDTO.fromUser(user), HttpStatus.OK);
        }
    }

    @GetMapping("/load_auth_user")
    public ResponseEntity<UserResponseDTO> loadAuthUser(){
        Member user = userService.loadAuthUser();
        if (user == null) return ResponseEntity.badRequest().build();
        else return new ResponseEntity<>(UserResponseDTO.fromUser(user), HttpStatus.OK);
    }
}
