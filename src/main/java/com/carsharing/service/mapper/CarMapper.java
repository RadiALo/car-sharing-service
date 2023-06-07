package com.carsharing.service.mapper;

import com.carsharing.dto.request.CarRequestDto;
import com.carsharing.dto.response.CarResponseDto;
import com.carsharing.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements DtoMapper<Car, CarRequestDto, CarResponseDto> {
    @Override
    public Car toModel(CarRequestDto dto) {
        Car car = new Car();
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setType(dto.getType());
        car.setInventory(dto.getInventory());
        car.setDailyFee(dto.getDailyFee());
        return car;
    }

    @Override
    public CarResponseDto toDto(Car model) {
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(model.getId());
        carResponseDto.setModel(model.getModel());
        carResponseDto.setBrand(model.getBrand());
        carResponseDto.setType(model.getType());
        carResponseDto.setInventory(model.getInventory());
        carResponseDto.setDailyFee(model.getDailyFee());
        return carResponseDto;
    }
}
