package com.carsharing.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class RentalRequestDto {
    @NotEmpty(message = "This field can`t be empty")
    private LocalDate rentalDate;
    @NotEmpty(message = "This field can`t be empty")
    private LocalDate returnDate;
    @NotEmpty(message = "This field can`t be empty")
    @Positive
    private Long carId;
    @NotEmpty(message = "This field can`t be empty")
    @Positive
    private Long userId;
}
