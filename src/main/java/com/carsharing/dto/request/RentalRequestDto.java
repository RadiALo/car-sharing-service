package com.carsharing.dto.request;

import java.util.Date;
import lombok.Data;

@Data
public class RentalRequestDto {
    private Date rentalDate;
    private Date returnDate;
    private Long carId;
    private Long userId;
}
