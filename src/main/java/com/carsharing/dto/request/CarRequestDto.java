package com.carsharing.dto.request;

import com.carsharing.model.Car;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CarRequestDto {
    @NotEmpty(message = "This field can`t be empty")
    private String model;
    @NotEmpty(message = "This field can`t be empty")
    private String brand;
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "This field can`t be empty")
    private Car.Type type;
    @NotEmpty(message = "This field can`t be empty")
    @PositiveOrZero
    private int inventory;
    @NotEmpty(message = "This field can`t be empty")
    @Positive
    private BigDecimal dailyFee;
}
