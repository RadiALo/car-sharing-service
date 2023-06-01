package com.carsharing.service.mapper;

import com.carsharing.dto.request.RentalRequestDto;
import com.carsharing.dto.response.RentalResponseDto;
import com.carsharing.model.Rental;
import com.carsharing.service.CarService;
import com.carsharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper implements RequestMapper<RentalRequestDto, Rental>,
        ResponseMapper<RentalResponseDto, Rental> {
    private final CarService carService;
    private final UserService userService;

    @Autowired
    public RentalMapper(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @Override
    public Rental toModel(RentalRequestDto dto) {
        Rental rental = new Rental();
        rental.setUser(userService.get(dto.getUserId()));
        rental.setCar(carService.findById(dto.getCarId()));
        rental.setRentalDate(dto.getRentalDate());
        rental.setReturnDate(dto.getReturnDate());
        return rental;
    }

    @Override
    public RentalResponseDto fromModel(Rental model) {
        RentalResponseDto rentalResponseDto = new RentalResponseDto();
        rentalResponseDto.setId(model.getId());
        rentalResponseDto.setCarId(model.getCar().getId());
        rentalResponseDto.setUserId(model.getCar().getId());
        rentalResponseDto.setRentalDate(model.getRentalDate());
        rentalResponseDto.setReturnDate(model.getReturnDate());
        rentalResponseDto.setActualReturnDate(model.getActualReturnDate());
        rentalResponseDto.setActive(model.isActive());
        return rentalResponseDto;
    }
}
