package com.carsharing.service;

import java.math.BigDecimal;

public interface PaymentStrategy {
    BigDecimal calculateFineByType(Long rentalId);
}
