package com.carsharing.service.mapper;

import com.carsharing.dto.request.RentalRequestDto;
import com.carsharing.dto.response.RentalResponseDto;
import com.carsharing.model.Car;
import com.carsharing.model.Rental;
import com.carsharing.model.User;

public class RentalMapper implements RequestMapper<RentalRequestDto, Rental>,
        ResponseMapper<RentalResponseDto, Rental> {

    @Override
    public Rental toModel(RentalRequestDto dto) {
        User user = new User();
        user.setId(dto.getUserId());
        Car car = new Car();
        car.setId(dto.getCarId());
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setCar(car);
        rental.setRentalDate(dto.getRentalDate());
        rental.setReturnDate(dto.getReturnDate());
        rental.setActualReturnDate(dto.getActualReturnDate());
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
        return rentalResponseDto;
    }
}
