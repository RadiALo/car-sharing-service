package com.example.carsharing.service;

import com.carsharing.model.Car;
import com.carsharing.model.Rental;
import com.carsharing.model.User;
import com.carsharing.service.RentalService;
import com.carsharing.service.impl.PaymentStrategyPayment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PaymentStrategyPaymentTest {
    private Rental testRental;
    private User testUser;
    private Car testCar;
    private BigDecimal dailyFee;

    @InjectMocks
    private PaymentStrategyPayment payment;

    @Mock
    private RentalService rentalService;

    @BeforeEach
    void init() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setFirstName("testFirstName");
        testUser.setSecondName("testSecondName");
        testUser.setPassword("testPassword");
        testUser.setEmail("testEmail");
        testUser.setRole(User.Role.CUSTOMER);

        testCar = new Car();
        testCar.setId(1L);
        testCar.setType(Car.Type.SEDAN);
        testCar.setModel("model");
        testCar.setBrand("brand");
        testCar.setInventory(5);
        testCar.setDailyFee(BigDecimal.valueOf(5.0));

        testRental = new Rental();
        testRental.setId(1L);
        testRental.setUser(testUser);
        testRental.setCar(testCar);
        testRental.setRentalDate(LocalDate.now());
        testRental.setActive(true);
        testRental.setReturnDate(LocalDate.of(2023, 06, 13));

        dailyFee = testRental.getCar().getDailyFee();
    }

    @Test
    void carNotReturned_notOk() {
        Mockito.when(rentalService.get(any(Long.class))).thenReturn(testRental);
        Assertions.assertThrows(RuntimeException.class,
                () -> payment.calculateFineByType(testRental.getId()));
    }

    @Test
    void rentalDurationIsZero_ok() {
        testRental.setActualReturnDate(LocalDate.now());
        Mockito.when(rentalService.get(any(Long.class))).thenReturn(testRental);
        Assertions.assertEquals(dailyFee.multiply(BigDecimal.valueOf(100)),
                payment.calculateFineByType(testRental.getId()));
    }

    @Test
    void rentalDurationBiggerThanZero_ok() {
        testRental.setActualReturnDate(LocalDate.of(2023, 06, 13));
        Mockito.when(rentalService.get(any(Long.class))).thenReturn(testRental);
        Assertions.assertEquals(BigDecimal.valueOf(-500),
                payment.calculateFineByType(testRental.getId()));
    }
}
