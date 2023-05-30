package com.carsharing.controller;

import com.carsharing.dto.CarRequestDto;
import com.carsharing.dto.CarResponseDto;
import com.carsharing.model.Car;
import com.carsharing.service.CarService;
import com.carsharing.service.mapper.RequestMapper;
import com.carsharing.service.mapper.ResponseMapper;
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
    private CarService carService;
    private ResponseMapper<CarResponseDto, Car> responseMapper;
    private RequestMapper<CarRequestDto, Car> requestMapper;

    @PostMapping
    public CarResponseDto add(@RequestBody CarRequestDto carRequestDto) {
        return responseMapper
                .fromModel(carService.save(requestMapper.toModel(carRequestDto)));
    }

    @GetMapping
    public List<CarResponseDto> findAllCars() {
        return carService.findAllCars()
                .stream()
                .map(responseMapper::fromModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CarResponseDto findById(@PathVariable Long id) {
        return responseMapper.fromModel(carService.findById(id));
    }

    @PutMapping("/{id}")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody CarRequestDto carRequestDto) {
        Car car = requestMapper.toModel(carRequestDto);
        car.setId(id);
        carService.save(car);
        return responseMapper.fromModel(car);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        carService.deleteById(id);
    }
}
