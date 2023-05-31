package com.carsharing.controller;

import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.model.Role;
import com.carsharing.model.User;
import com.carsharing.service.UserService;
import com.carsharing.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PutMapping("/{id}/role")
    private UserResponseDto updateRole(@PathVariable Long id,
                                       @RequestBody Role role) {
        User user = userService.get(id);
        user.setRole(role);
        userService.update(user);
        return userMapper.fromModel(user);
    }

    @GetMapping("/users/me")
    private UserResponseDto getMe(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        User user = userService.findByEmail(details.getUsername()).orElseThrow();
        return userMapper.fromModel(user);
    }

    @PutMapping("/users/me")
    private UserResponseDto updateMe(Authentication auth,
                                     @RequestBody UserRequestDto userRequestDto) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        User user = userMapper.toModel(userRequestDto);
        user.setId(userService.findByEmail(details
                .getUsername()).orElseThrow().getId());
        userService.update(user);
        return userMapper.fromModel(user);
    }
}
