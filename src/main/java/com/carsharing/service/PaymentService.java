package com.carsharing.service;

import com.carsharing.model.Payment;
import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    Payment save(Payment payment);

    Payment findBySessionId(String id);

    Payment update(Payment payment);

    Payment createStripeSession(Long rentalId, Payment.Type type);

    boolean isSessionPaid(String sessionId);

    List<Payment> findByUserId(Long id);

    BigDecimal calculateAmountToPay(Long rentalId, Payment.Type type);
}
