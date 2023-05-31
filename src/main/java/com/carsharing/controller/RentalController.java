package com.carsharing.controller;

import com.carsharing.dto.request.RentalRequestDto;
import com.carsharing.dto.response.RentalResponseDto;
import com.carsharing.model.Car;
import com.carsharing.model.Rental;
import com.carsharing.service.CarService;
import com.carsharing.service.RentalService;
import com.carsharing.service.mapper.RequestMapper;
import com.carsharing.service.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;
    private final RequestMapper<RentalRequestDto, Rental> requestMapper;
    private final ResponseMapper<RentalResponseDto, Rental> responseMapper;
    private final CarService carService;

    @PostMapping
    public RentalResponseDto add(@RequestBody RentalRequestDto requestDto) {
        Rental rentalToModel = requestMapper.toModel(requestDto);
        Rental rental =rentalService.save(rentalToModel);
        Car car = rental.getCar();
        carService.inventoryDecrease(car);
        return responseMapper.fromModel(rental);
    }

    @GetMapping("/{id}")
    public RentalResponseDto findById(@PathVariable Long id) {
        return responseMapper.fromModel(rentalService.get(id));
    }

    @GetMapping("/user_id={id}")
    public List<RentalResponseDto> findByUserId(@PathVariable Long id) {
        return rentalService.getByUserId(id)
                .stream()
                .map(responseMapper::fromModel)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/return")
    public RentalResponseDto setActualReturnDate(@PathVariable Long id,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date actualTime) {
        Rental rental = rentalService.get(id);
        Car car = rental.getCar();
        carService.inventoryIncrease(car);
        rental.setActualReturnDate(actualTime);
        rentalService.save(rental);
        return responseMapper.fromModel(rental);
    }
}
