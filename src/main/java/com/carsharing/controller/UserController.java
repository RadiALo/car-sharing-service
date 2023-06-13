package com.carsharing.controller;

import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.model.User;
import com.carsharing.service.UserService;
import com.carsharing.service.mapper.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final DtoMapper<User, UserRequestDto, UserResponseDto> dtoMapper;

    @PutMapping("/{id}/role")
    @Operation(summary = "Update user role")
    private UserResponseDto updateRole(
            @Parameter(description = "id of user to be updated")
            @PathVariable Long id,
            @Parameter(description = "User role: Customer or Manager", required = true,
                    schema = @Schema(type = "string", defaultValue = "CUSTOMER"))
            @RequestParam User.Role role) {
        User user = userService.get(id);
        user.setRole(role);
        userService.update(user);
        return dtoMapper.toDto(user);
    }
  
    @GetMapping("/me")
    @Operation(summary = "Get user by Authentication information")
    private UserResponseDto getMe(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        User user = userService.findByEmail(details.getUsername());
        return dtoMapper.toDto(user);
    }
  
    @PutMapping("/me")
    @Operation(summary = "Update user information")
    private UserResponseDto updateMe(
            Authentication auth,
            @Parameter(description = "User to be updated", required = true,
                    content = @Content(schema = @Schema(implementation = UserRequestDto.class)))
            @RequestBody @Valid UserRequestDto userRequestDto) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        User user = dtoMapper.toModel(userRequestDto);
        user.setId(userService.findByEmail(details
                .getUsername()).getId());
        userService.update(user);
        return dtoMapper.toDto(user);
    }
}
