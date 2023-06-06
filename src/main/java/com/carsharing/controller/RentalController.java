package com.carsharing.controller;

import com.carsharing.dto.request.RentalRequestDto;
import com.carsharing.dto.response.RentalResponseDto;
import com.carsharing.model.Car;
import com.carsharing.model.Rental;
import com.carsharing.service.CarService;
import com.carsharing.service.NotificationService;
import com.carsharing.service.RentalService;
import com.carsharing.service.mapper.RequestMapper;
import com.carsharing.service.mapper.ResponseMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;
    private final RequestMapper<RentalRequestDto, Rental> requestMapper;
    private final ResponseMapper<RentalResponseDto, Rental> responseMapper;
    private final CarService carService;
    private final NotificationService notificationService;

    @PostMapping
    public RentalResponseDto add(@RequestBody RentalRequestDto requestDto) {
        Rental rental = requestMapper.toModel(requestDto);
        Car car = rental.getCar();
        rental.setActive(true);
        if (car.getInventory() > 0) {
            rentalService.save(rental);
            notificationService.sendNotificationAboutPossibleRental(rental);
            carService.inventoryDecrease(car);
            return responseMapper.fromModel(rental);
        }
        notificationService.sendNotificationAboutImpossibleRental(rental);
        return new RentalResponseDto();
    }

    @GetMapping("/{id}")
    public RentalResponseDto findById(@PathVariable Long id) {
        return responseMapper.fromModel(rentalService.get(id));
    }

    @GetMapping("/user_id={id}")
    public List<RentalResponseDto> findByUserId(@PathVariable Long id,
                                                @RequestParam boolean isActive) {
        return rentalService.getByUserId(id, isActive)
                .stream()
                .map(responseMapper::fromModel)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/return")
    public RentalResponseDto setActualReturnDate(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate actualTime) {
        Rental rental = rentalService.get(id);
        Car car = rental.getCar();
        carService.inventoryIncrease(car);
        rental.setActualReturnDate(actualTime);
        rental.setActive(false);
        rentalService.save(rental);
        return responseMapper.fromModel(rental);
    }
}
