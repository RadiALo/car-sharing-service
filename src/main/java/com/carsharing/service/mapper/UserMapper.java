package com.carsharing.service.mapper;

import com.carsharing.dto.request.UserRequestDto;
import com.carsharing.dto.response.UserResponseDto;
import com.carsharing.model.Role;
import com.carsharing.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestMapper<UserRequestDto, User>,
        ResponseMapper<UserResponseDto, User> {
    @Override
    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setSecondName(dto.getSecondName());
        user.setRole(Role.valueOf(dto.getRole()));
        return user;
    }

    @Override
    public UserResponseDto fromModel(User model) {
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
