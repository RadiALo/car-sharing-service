package com.carsharing.service;

import com.carsharing.model.Car;
import java.util.List;

public interface CarService {
    Car save(Car car);

    Car findById(Long id);

    void deleteById(Long id);

    List<Car> findAllCars();

    Car inventoryDecrease(Car car);

    Car inventoryIncrease(Car car);
}
