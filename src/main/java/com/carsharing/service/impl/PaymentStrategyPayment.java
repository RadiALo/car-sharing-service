package com.carsharing.service.impl;

import com.carsharing.model.Rental;
import com.carsharing.service.PaymentStrategy;
import com.carsharing.service.RentalService;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentStrategyPayment implements PaymentStrategy {
    private static final BigDecimal numberToConvertFromCentsToDollars = BigDecimal.valueOf(100);
    private final RentalService rentalService;

    @Override
    public BigDecimal calculateFineByType(Long rentalId) {
        final Rental rental = rentalService.get(rentalId);
        if (rental.getActualReturnDate() == null) {
            throw new RuntimeException("You haven't returned the car yet");
        }
        long rentalDuration =
                ChronoUnit.DAYS.between(rental.getActualReturnDate(), rental.getRentalDate());
        BigDecimal dailyFee = rental.getCar().getDailyFee();
        if (rentalDuration == 0) {
            return dailyFee.multiply(numberToConvertFromCentsToDollars);
        }
        final long amountToPay = rentalDuration * dailyFee.longValue();
        return BigDecimal.valueOf(amountToPay).multiply(numberToConvertFromCentsToDollars);
    }
}
