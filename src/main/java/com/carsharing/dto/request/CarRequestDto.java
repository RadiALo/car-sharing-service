package com.carsharing.dto.request;

import com.carsharing.model.Car;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CarRequestDto {
    @Schema(example = "A7")
    @NotEmpty(message = "This field can`t be empty")
    private String model;
    @Schema(example = "Audi")
    @NotEmpty(message = "This field can`t be empty")
    private String brand;
    @Schema(example = "SEDAN")
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "This field can`t be empty")
    private Car.Type type;
    @Schema(example = "1")
    @NotEmpty(message = "This field can`t be empty")
    @PositiveOrZero
    private int inventory;
    @Schema(example = "30")
    @NotEmpty(message = "This field can`t be empty")
    @Positive
    private BigDecimal dailyFee;
}
