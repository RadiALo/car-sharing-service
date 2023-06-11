package com.carsharing.controller;

import com.carsharing.dto.request.CarRequestDto;
import com.carsharing.dto.response.CarResponseDto;
import com.carsharing.model.Car;
import com.carsharing.service.CarService;
import com.carsharing.service.mapper.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final DtoMapper<Car, CarRequestDto, CarResponseDto> dtoMapper;

    @PostMapping
    @Operation(summary = "Create a new car for rental service")
    public CarResponseDto add(
            @Parameter(description = "Car to add to rental service", required = true,
                    content = @Content(schema = @Schema(implementation =
                            CarRequestDto.class)))
            @RequestBody @Valid CarRequestDto carRequestDto) {
        return dtoMapper
                .toDto(carService.save(dtoMapper.toModel(carRequestDto)));
    }

    @GetMapping
    @Operation(summary = "Find all available cars")
    public List<CarResponseDto> findAllCars() {
        return carService.findAllCars()
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find car by id")
    public CarResponseDto findById(@Parameter(description = "id of searched car")
                                       @PathVariable Long id) {
        return dtoMapper.toDto(carService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update car information")
    public CarResponseDto update(
            @Parameter(description = "id of car to be updated")
            @PathVariable Long id,
            @Parameter(description = "New car information to update", required = true,
                    content = @Content(schema = @Schema(implementation =
                            CarRequestDto.class)))
            @RequestBody @Valid CarRequestDto carRequestDto) {
        Car car = dtoMapper.toModel(carRequestDto);
        car.setId(id);
        carService.save(car);
        return dtoMapper.toDto(car);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete car from rental service by id")
    public void delete(@Parameter(description = "id of car to be deleted")
                           @PathVariable Long id) {
        carService.deleteById(id);
    }
}
