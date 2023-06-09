package com.example.carsharing.service;

import com.carsharing.exception.EmptyCarInventoryException;
import com.carsharing.model.Car;
import com.carsharing.repository.CarRepository;
import com.carsharing.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @Test
    void inventoryDecreaseCounter_ok() {
        Car car = new Car();
        car.setInventory(1);
        carService.inventoryDecrease(car);

        Assertions.assertEquals(0, car.getInventory());
    }

    @Test
    void inventoryIncreaseCounter_ok(){
        Car car = new Car();
        car.setInventory(0);
        carService.inventoryIncrease(car);

        Assertions.assertEquals(1, car.getInventory());
    }

    @Test
    void inventoryIsZero_notOk() {
        Car car = new Car();
        car.setInventory(0);
        Assertions.assertThrows(EmptyCarInventoryException.class,
                () -> carService.inventoryDecrease(car));
    }
}
