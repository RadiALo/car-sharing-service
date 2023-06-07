package com.carsharing.service;

import com.carsharing.model.Payment;
import java.util.List;

public interface PaymentService {
    Payment save(Payment payment);

    Payment findBySessionId(String id);

    Payment update(Payment payment);

    boolean isSessionPaid(String sessionId);

    List<Payment> findByUserId(Long id);
}
