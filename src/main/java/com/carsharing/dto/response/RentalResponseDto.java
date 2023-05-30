package com.carsharing.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class RentalResponseDto {
    private Long id;
    private Date rentalDate;
    private Date returnDate;
    private Date actualReturnDate;
    private Long carId;
    private Long userId;
}
