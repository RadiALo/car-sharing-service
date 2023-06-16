package com.carsharing.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @Schema(example = "test@gmail.com")
    @NotEmpty(message = "This field can`t be empty")
    @Size(min = 4)
    private String email;
    @Schema(example = "Elon")
    @NotEmpty(message = "This field can`t be empty")
    private String firstName;
    @Schema(example = "Mask")
    @Size(min = 1)
    private String secondName;
    @Schema(example = "12345678")
    @NotEmpty(message = "This field can`t be empty")
    @Size(min = 8, max = 20)
    private String password;
}
