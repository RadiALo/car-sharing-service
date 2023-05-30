package com.carsharing.controller;

import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.service.AuthenticationService;
import com.carsharing.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userDtoMapper;


    @PostMapping("/register")
    private UserResponseDto register(@RequestBody UserRequestDto requestDto) {
        return userDtoMapper.fromModel(authenticationService.register(
                requestDto.getEmail(), requestDto.getPassword()));
    }

    @PostMapping("/login")
    private String login(Authentication auth) {
        // JWT needed
        return null;
    }
}
