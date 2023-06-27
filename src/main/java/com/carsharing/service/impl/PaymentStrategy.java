package com.carsharing.service.impl;

import com.carsharing.model.Payment;
import com.carsharing.service.PaymentHandler;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PaymentStrategy {
    private Map<Payment.Type, PaymentHandler> handlerMap;

    public PaymentStrategy(List<PaymentHandler> paymentHandlers) {
        this.handlerMap = paymentHandlers.stream()
                .collect(Collectors.toMap(this::getPaymentType, Function.identity()));
    }

    public com.carsharing.service.PaymentHandler getPaymentHandler(Payment.Type paymentType) {
        return handlerMap.get(paymentType);
    }

    private Payment.Type getPaymentType(PaymentHandler paymentStrategy) {
        if (paymentStrategy instanceof PaymentHandlerPayment) {
            return Payment.Type.PAYMENT;
        } else if (paymentStrategy instanceof PaymentHandlerFine) {
            return Payment.Type.FINE;
        }
        throw new IllegalArgumentException("Unsupported PaymentStrategy: "
                + paymentStrategy.getClass().getSimpleName());
    }
}
