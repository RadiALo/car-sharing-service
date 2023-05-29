package com.carsharing.dto;

import com.carsharing.model.CarType;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String model;
    private String brand;
    private CarType type;
    private int inventory;
    private BigDecimal dailyFee;
}
