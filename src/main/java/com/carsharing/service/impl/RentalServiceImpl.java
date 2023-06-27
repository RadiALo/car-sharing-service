package com.carsharing.service.impl;

import com.carsharing.exception.NoSuchRentalIdException;
import com.carsharing.exception.ReturnedRentalException;
import com.carsharing.model.Car;
import com.carsharing.model.Rental;
import com.carsharing.repository.RentalRepository;
import com.carsharing.service.CarService;
import com.carsharing.service.NotificationService;
import com.carsharing.service.RentalService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final NotificationService notificationService;
    private final CarService carService;

    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public Rental createRentalWithNotificationAndDecrementingInventory(Rental rental) {
        Car car = rental.getCar();
        rental.setActive(true);
        if (car.getInventory() > 0) {
            save(rental);
            notificationService.sendNotificationAboutPossibleRental(rental);
            carService.inventoryDecrease(car);
            return rental;
        }
        notificationService.sendNotificationAboutImpossibleRental(rental);
        return new Rental();
    }

    @Override
    public Rental get(Long id) {
        return rentalRepository.findById(id).orElseThrow(() ->
                new NoSuchRentalIdException("Can`t find rental by id:" + id));
    }

    @Override
    public List<Rental> getByUserId(Long userId, boolean isActive) {
        if (isActive) {
            return rentalRepository.findRentalByActiveIsTrueAndUserId(userId);
        }
        return rentalRepository.findRentalByUserId(userId);
    }

    @Override
    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental setActualReturnDate(Long id, LocalDate actualTime) {
        Rental rental = get(id);
        Car car = rental.getCar();
        if (rental.getActualReturnDate() == null) {
            carService.inventoryIncrease(car);
            rental.setActualReturnDate(actualTime);
            rental.setActive(false);
            save(rental);
            notificationService.sentNotificationAboutReturnedCar(rental);
            return rental;
        }
        throw new ReturnedRentalException("This rental can't returned twice!");
    }
}
