package com.example.carsharing.service;

import com.carsharing.exception.EmptyCarInventoryException;
import com.carsharing.model.Car;
import com.carsharing.repository.CarRepository;
import com.carsharing.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    private Car testCar;
    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void init() {
        testCar = new Car();
    }

    @Test
    void inventoryDecreaseCounter_ok() {
        testCar.setInventory(1);
        carService.inventoryDecrease(testCar);

        Assertions.assertEquals(0, testCar.getInventory());
    }

    @Test
    void inventoryIncreaseCounter_ok(){
        testCar.setInventory(0);
        carService.inventoryIncrease(testCar);

        Assertions.assertEquals(1, testCar.getInventory());
    }

    @Test
    void inventoryIsZero_notOk() {
        testCar.setInventory(0);
        Assertions.assertThrows(EmptyCarInventoryException.class,
                () -> carService.inventoryDecrease(testCar));
    }
}
