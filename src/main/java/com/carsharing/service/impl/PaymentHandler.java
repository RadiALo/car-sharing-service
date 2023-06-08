package com.carsharing.service.impl;

import com.carsharing.model.Payment;
import com.carsharing.service.PaymentStrategy;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PaymentHandler {
    private Map<Payment.Type, PaymentStrategy> handlerMap;

    public PaymentHandler(List<PaymentStrategy> paymentHandlers) {
        this.handlerMap = paymentHandlers.stream()
                .collect(Collectors.toMap(this::getPaymentType, Function.identity()));
    }

    public PaymentStrategy getPaymentHandler(Payment.Type paymentType) {
        return handlerMap.get(paymentType);
    }

    private Payment.Type getPaymentType(PaymentStrategy paymentStrategy) {
        if (paymentStrategy instanceof PaymentStrategyPayment) {
            return Payment.Type.PAYMENT;
        } else if (paymentStrategy instanceof PaymentStrategyFine) {
            return Payment.Type.FINE;
        }
        throw new IllegalArgumentException("Unsupported PaymentStrategy: "
                + paymentStrategy.getClass().getSimpleName());
    }
}
