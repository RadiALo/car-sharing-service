package com.carsharing.service;

import com.carsharing.model.Rental;
import java.util.List;

public interface RentalService {
    Rental save(Rental rental);

    Rental get(Long id);

    List<Rental> getByUserId(Long id, boolean isActive);
}
