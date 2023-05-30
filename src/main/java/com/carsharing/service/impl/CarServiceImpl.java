package com.carsharing.service.impl;

import com.carsharing.model.Car;
import com.carsharing.repository.CarRepository;
import com.carsharing.service.CarService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Can't find car by such id " + id));
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }
}
