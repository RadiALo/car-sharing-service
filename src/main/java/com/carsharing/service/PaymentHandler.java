package com.carsharing.service;

import java.math.BigDecimal;

public interface PaymentHandler {
    BigDecimal calculateFineByType(Long rentalId);
}
