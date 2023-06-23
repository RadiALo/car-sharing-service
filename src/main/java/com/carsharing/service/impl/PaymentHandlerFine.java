package com.carsharing.service.impl;

import com.carsharing.model.Rental;
import com.carsharing.service.PaymentHandler;
import com.carsharing.service.RentalService;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentHandlerFine implements PaymentHandler {
    private static final BigDecimal FINE = BigDecimal.valueOf(0.15);
    private static final BigDecimal numberToConvertFromCentsToDollars = BigDecimal.valueOf(100);
    private final RentalService rentalService;

    @Override
    public BigDecimal calculateFineByType(Long rentalId) {
        final Rental rental = rentalService.get(rentalId);
        if (rental.getActualReturnDate() == null) {
            throw new RuntimeException("You haven't returned the car yet");
        }
        long rentalDuration =
                ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getActualReturnDate());
        long rentalDurationWithFine =
                ChronoUnit.DAYS.between(rental.getReturnDate(), rental.getActualReturnDate());
        BigDecimal dailyFee = rental.getCar().getDailyFee();
        final long amountToPay = ((rentalDurationWithFine * dailyFee.longValue())
                + (rentalDurationWithFine * dailyFee.longValue() * FINE.longValue()))
                + (rentalDuration * dailyFee.longValue());
        return BigDecimal.valueOf(amountToPay).multiply(numberToConvertFromCentsToDollars);
    }
}
