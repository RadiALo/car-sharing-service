package com.carsharing.service.impl;

import com.carsharing.model.Rental;
import com.carsharing.repository.RentalRepository;
import com.carsharing.service.NotificationService;
import com.carsharing.service.RentalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final NotificationService notificationService;

    @Override
    public Rental save(Rental rental) {
        if (rental.getCar().getInventory() > 0) {
            notificationService.sendNotificationAboutPossibleRental(rental);
            return rentalRepository.save(rental);
        }
        notificationService.sendNotificationAboutImpossibleRental(rental);
        return new Rental();
    }

    @Override
    public Rental get(Long id) {
        return rentalRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can`t find rental by id:" + id));
    }

    @Override
    public List<Rental> getByUserId(Long userId, boolean isActive) {
        if (isActive) {
            return rentalRepository.findRentalByActiveIsTrueAndUserId(userId);
        }
        return rentalRepository.findRentalByUserId(userId);
    }
}
