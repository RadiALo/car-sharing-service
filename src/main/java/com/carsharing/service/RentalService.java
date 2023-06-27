package com.carsharing.service;

import com.carsharing.model.Rental;
import java.time.LocalDate;
import java.util.List;

public interface RentalService {
    Rental save(Rental rental);

    Rental createRentalWithNotificationAndDecrementingInventory(Rental rental);

    Rental get(Long id);

    List<Rental> getByUserId(Long id, boolean isActive);

    List<Rental> findAll();

    Rental setActualReturnDate(Long id, LocalDate actualTime);
}
