package com.carsharing.service.mapper;

import com.carsharing.dto.request.RentalRequestDto;
import com.carsharing.dto.response.RentalResponseDto;
import com.carsharing.model.Rental;
import com.carsharing.service.CarService;
import com.carsharing.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RentalMapper implements DtoMapper<Rental, RentalRequestDto, RentalResponseDto> {
    private final CarService carService;
    private final UserService userService;

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
    public RentalResponseDto toDto(Rental model) {
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
