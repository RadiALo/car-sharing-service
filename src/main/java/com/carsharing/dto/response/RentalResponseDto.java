package com.carsharing.dto.response;

import java.util.Date;
import lombok.Data;

@Data
public class RentalResponseDto {
    private Long id;
    private Date rentalDate;
    private Date returnDate;
    private Date actualReturnDate;
    private Long carId;
    private Long userId;
}

