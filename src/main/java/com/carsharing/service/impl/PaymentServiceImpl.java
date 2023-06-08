package com.carsharing.service.impl;

import com.carsharing.model.Payment;
import com.carsharing.repository.PaymentRepository;
import com.carsharing.service.PaymentService;
import com.carsharing.service.PaymentStrategy;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentHandler paymentHandler;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment findBySessionId(String id) {
        return paymentRepository.findBySessionId(id);
    }

    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findByUserId(Long id) {
        return paymentRepository.findPaymentsByRentalUserId(id);
    }

    @Override
    public BigDecimal calculateAmountToPay(Long rentalId, Payment.Type type) {
        PaymentStrategy paymentStrategy = paymentHandler.getPaymentHandler(type);
        return paymentStrategy.calculateFineByType(rentalId);
    }

    public boolean isSessionPaid(String sessionId) {
        return paymentRepository.findBySessionId(sessionId).getStatus() == Payment.Status.PAID;
    }
}
