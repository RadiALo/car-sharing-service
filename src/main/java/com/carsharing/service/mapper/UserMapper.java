package com.carsharing.service.mapper;

import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.model.User;
import com.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper implements DtoMapper<User, UserRequestDto, UserResponseDto> {
    private final UserService userService;

    @Override
    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setSecondName(dto.getSecondName());
        return user;
    }

    @Override
    public UserResponseDto toDto(User model) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(model.getId());
        userResponseDto.setEmail(model.getEmail());
        userResponseDto.setPassword(model.getPassword());
        userResponseDto.setFirstName(model.getFirstName());
        userResponseDto.setSecondName(model.getSecondName());
        userResponseDto.setRole(model.getRole().name());
        return userResponseDto;
    }
}
