package com.carsharing.service.impl;

import com.carsharing.model.Payment;
import com.carsharing.repository.PaymentRepository;
import com.carsharing.service.PaymentHandler;
import com.carsharing.service.PaymentService;
import com.carsharing.service.RentalService;
import com.carsharing.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final RentalService rentalService;
    private final StripeService stripeService;
    private final PaymentRepository paymentRepository;
    private final PaymentStrategy paymentStrategy;

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
    public Payment createStripeSession(Long rentalId, Payment.Type type) {
        SessionCreateParams params = stripeService.createPaymentSession(
                rentalId, type);
        try {
            Session session = Session.create(params);
            String sessionUrl = session.getUrl();
            String sessionId = session.getId();
            BigDecimal amountToPay = BigDecimal.valueOf(session.getAmountTotal());
            Payment payment = new Payment();
            payment.setSessionId(sessionId);
            payment.setSessionUrl(sessionUrl);
            payment.setType(type);
            payment.setStatus(Payment.Status.PENDING);
            payment.setAmount(amountToPay);
            payment.setRental(rentalService.get(rentalId));
            return payment;
        } catch (StripeException e) {
            throw new RuntimeException("Can't get payment page.", e);
        }
    }

    @Override
    public List<Payment> findByUserId(Long id) {
        return paymentRepository.findPaymentsByRentalUserId(id);
    }

    @Override
    public BigDecimal calculateAmountToPay(Long rentalId, Payment.Type type) {
        PaymentHandler paymentStrategy = this.paymentStrategy.getPaymentHandler(type);
        return paymentStrategy.calculateFineByType(rentalId);
    }

    public boolean isSessionPaid(String sessionId) {
        return paymentRepository.findBySessionId(sessionId).getStatus() == Payment.Status.PAID;
    }
}
