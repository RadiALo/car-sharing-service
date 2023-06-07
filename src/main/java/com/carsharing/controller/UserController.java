package com.carsharing.controller;

import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.model.User;
import com.carsharing.service.UserService;
import com.carsharing.service.mapper.DtoMapper;
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
    private final DtoMapper<User, UserRequestDto, UserResponseDto> dtoMapper;

    @PutMapping("/{id}/role")
    private UserResponseDto updateRole(@PathVariable Long id,
                                       @RequestBody User.Role role) {
        User user = userService.get(id);
        user.setRole(role);
        userService.update(user);
        return dtoMapper.toDto(user);
    }

    @GetMapping("/users/me")
    private UserResponseDto getMe(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        User user = userService.findByEmail(details.getUsername());
        return dtoMapper.toDto(user);
    }

    @PutMapping("/users/me")
    private UserResponseDto updateMe(Authentication auth,
                                     @RequestBody UserRequestDto userRequestDto) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        User user = dtoMapper.toModel(userRequestDto);
        user.setId(userService.findByEmail(details
                .getUsername()).getId());
        userService.update(user);
        return dtoMapper.toDto(user);
    }
}
