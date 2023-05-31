package com.carsharing.dto.request;

import lombok.Data;
import java.util.Date;

@Data
public class RentalRequestDto {
    private Date rentalDate;
    private Date returnDate;
    private Long carId;
    private Long userId;
}
