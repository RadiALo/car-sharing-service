package com.carsharing.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalRequestDto {
    @Schema(example = "2023-07-27")
    @NotEmpty(message = "This field can`t be empty")
    private LocalDate rentalDate;
    @Schema(example = "2023-07-28")
    @NotEmpty(message = "This field can`t be empty")
    private LocalDate returnDate;
    @Schema(example = "1")
    @NotEmpty(message = "This field can`t be empty")
    @Positive
    private Long carId;
    @Schema(example = "1")
    @NotEmpty(message = "This field can`t be empty")
    @Positive
    private Long userId;
}
