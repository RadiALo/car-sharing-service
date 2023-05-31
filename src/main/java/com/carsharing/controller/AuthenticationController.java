package com.carsharing.controller;

import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.dto.response.TokenResponseDto;
import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.model.User;
import com.carsharing.security.jwt.JwtTokenProvider;
import com.carsharing.service.AuthenticationService;
import com.carsharing.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userDtoMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    private UserResponseDto register(@RequestBody UserRequestDto requestDto) {
        return userDtoMapper.fromModel(authenticationService.register(
                requestDto.getEmail(), requestDto.getPassword()));
    }

    @PostMapping("/login")
    private TokenResponseDto login(@RequestParam String login,
                         @RequestParam String password) {
        User user = authenticationService.login(login, password);
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken(token);
        return tokenResponseDto;
    }
}
