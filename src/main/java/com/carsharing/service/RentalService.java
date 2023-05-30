package com.carsharing.service;

import java.util.List;
import com.carsharing.model.Rental;

public interface RentalService {
    Rental save(Rental rental);

    Rental get(Long id);

    List<Rental> getByUserId(Long id);
}
