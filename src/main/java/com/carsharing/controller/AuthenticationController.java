package com.carsharing.controller;

import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.dto.response.TokenResponseDto;
import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.model.User;
import com.carsharing.security.jwt.JwtTokenProvider;
import com.carsharing.service.AuthenticationService;
import com.carsharing.service.mapper.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@EnableWebSecurity
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final DtoMapper<User, UserRequestDto, UserResponseDto> dtoMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public UserResponseDto register(
            @Parameter(description = "User to register.", required = true,
                    content = @Content(schema =
                    @Schema(implementation = UserRequestDto.class)))
            @RequestBody @Valid UserRequestDto requestDto) {
        return dtoMapper.toDto(
                authenticationService.register(dtoMapper.toModel(requestDto)));
    }

    @PostMapping("/login")
    @Operation(summary = "Login in system")
    public TokenResponseDto login(
            @Parameter(description = "Write your login", required = true,
            schema = @Schema(defaultValue = "test@gmail.com"))
            @RequestParam @Valid String login,
            @Parameter(description = "Write your password", required = true,
            schema = @Schema(defaultValue = "12345678"))
            @RequestParam @Valid String password) {
        User user = authenticationService.login(login, password);
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken(token);
        return tokenResponseDto;
    }
}
