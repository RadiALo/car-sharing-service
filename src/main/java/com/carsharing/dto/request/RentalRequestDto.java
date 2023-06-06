package com.carsharing.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RentalRequestDto {
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private Long carId;
    private Long userId;
}
