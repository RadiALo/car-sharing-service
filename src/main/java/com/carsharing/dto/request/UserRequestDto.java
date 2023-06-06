package com.carsharing.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotEmpty(message = "This field can`t be empty")
    @Size(min = 4)
    private String email;
    @NotEmpty(message = "This field can`t be empty")
    private String firstName;
    @Size(min = 1)
    private String secondName;
    @NotEmpty(message = "This field can`t be empty")
    @Size(min = 8, max = 20)
    private String password;
    private String role;
}
