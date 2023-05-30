package com.carsharing.service.impl;

import java.util.List;
import com.carsharing.model.Rental;
import com.carsharing.repository.RentalRepository;
import com.carsharing.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public Rental get(Long id) {
        return rentalRepository.getReferenceById(id);
    }

    @Override
    public List<Rental> getByUserId(Long id) {
        return rentalRepository.getRentalsByUserId(id);
    }
}
