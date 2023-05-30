package com.carsharing.service.impl;

import com.carsharing.model.Rental;
import com.carsharing.repository.RentalRepository;
import com.carsharing.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return rentalRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can`t find rental by id:" + id));
    }

    @Override
    public List<Rental> getByUserId(Long userId) {
        return rentalRepository.findRentalByUserId(userId);
    }
}
