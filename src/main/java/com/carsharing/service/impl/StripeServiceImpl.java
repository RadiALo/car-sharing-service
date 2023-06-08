package com.carsharing.service.impl;

import com.carsharing.model.Payment;
import com.carsharing.service.PaymentService;
import com.carsharing.service.StripeService;
import com.stripe.Stripe;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StripeServiceImpl implements StripeService {
    private static final String PAYMENT_URL = "http://localhost:6868/payments";
    private final PaymentService paymentService;
    @Value("${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;

    @Override
    public SessionCreateParams createPaymentSession(Long rentalId, Payment.Type type) {
        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams.Builder builder = new SessionCreateParams.Builder();
        builder.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD);
        builder.setMode(SessionCreateParams.Mode.PAYMENT);
        builder.setSuccessUrl(PAYMENT_URL + "/success" + "?session_id={CHECKOUT_SESSION_ID}");
        builder.setCancelUrl(PAYMENT_URL + "/cancel");
        builder.addLineItem(
                new SessionCreateParams.LineItem.Builder()
                        .setPriceData(
                                new SessionCreateParams.LineItem.PriceData.Builder()
                                        .setCurrency("usd")
                                        .setProductData(
                                                new SessionCreateParams
                                                        .LineItem
                                                        .PriceData
                                                        .ProductData.Builder()
                                                        .setName("Car Rental Payment")
                                                        .build()
                                        )
                                        .setUnitAmount(
                                                paymentService.calculateAmountToPay(
                                                        rentalId, type).longValue()
                                        )
                                        .build()
                        )
                        .setQuantity(1L)
                        .build()
        );
        return builder.build();
    }
}
