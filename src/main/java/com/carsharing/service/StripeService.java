package com.carsharing.service;

import com.carsharing.model.Payment;
import com.stripe.param.checkout.SessionCreateParams;

public interface StripeService {
    SessionCreateParams createPaymentSession(Long rentalId, Payment.Type type);
}
