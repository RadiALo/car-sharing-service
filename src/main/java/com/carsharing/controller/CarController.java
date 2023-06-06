package com.carsharing.controller;

import com.carsharing.dto.request.CarRequestDto;
import com.carsharing.dto.response.CarResponseDto;
import com.carsharing.model.Car;
import com.carsharing.service.CarService;
import com.carsharing.service.mapper.DtoMapper;
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
    public CarResponseDto add(@RequestBody CarRequestDto carRequestDto) {
        return dtoMapper
                .toDto(carService.save(dtoMapper.toModel(carRequestDto)));
    }

    @GetMapping
    public List<CarResponseDto> findAllCars() {
        return carService.findAllCars()
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CarResponseDto findById(@PathVariable Long id) {
        return dtoMapper.toDto(carService.findById(id));
    }

    @PutMapping("/{id}")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody CarRequestDto carRequestDto) {
        Car car = dtoMapper.toModel(carRequestDto);
        car.setId(id);
        carService.save(car);
        return dtoMapper.toDto(car);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        carService.deleteById(id);
    }
}
