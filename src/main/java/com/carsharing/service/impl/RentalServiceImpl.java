package com.carsharing.service.impl;

import com.carsharing.exception.NoSuchRentalIdException;
import com.carsharing.model.Rental;
import com.carsharing.repository.RentalRepository;
import com.carsharing.service.RentalService;
import java.util.List;
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
}
