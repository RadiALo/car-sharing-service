package com.carsharing.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstName;
    private String secondName;
    private String password;
    private String role;
}
