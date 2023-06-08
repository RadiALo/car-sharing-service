package com.carsharing.controller;

import com.carsharing.dto.request.PaymentRequestDto;
import com.carsharing.dto.response.PaymentResponseDto;
import com.carsharing.model.Payment;
import com.carsharing.service.PaymentService;
import com.carsharing.service.StripeService;
import com.carsharing.service.mapper.DtoMapper;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final StripeService stripeService;
    private final PaymentService paymentService;
    private final DtoMapper<Payment, PaymentRequestDto, PaymentResponseDto> dtoMapper;

    @PostMapping
    public PaymentResponseDto createStripeSession(
            @RequestBody PaymentRequestDto paymentRequestDto) {
        SessionCreateParams params = stripeService.createPaymentSession(
                paymentRequestDto.getRentalId(), paymentRequestDto.getType());
        try {
            Session session = Session.create(params);
            String sessionUrl = session.getUrl();
            String sessionId = session.getId();
            BigDecimal amountToPay = BigDecimal.valueOf(session.getAmountTotal());
            PaymentRequestDto requestDto = new PaymentRequestDto();
            requestDto.setSessionId(sessionId);
            requestDto.setSessionUrl(sessionUrl);
            requestDto.setType(paymentRequestDto.getType());
            requestDto.setStatus(Payment.Status.PENDING);
            requestDto.setAmount(amountToPay);
            requestDto.setRentalId(paymentRequestDto.getRentalId());
            return dtoMapper.toDto(paymentService.save(dtoMapper.toModel(requestDto)));
        } catch (StripeException e) {
            throw new RuntimeException("Can't get payment page.", e);
        }
    }

    @GetMapping("/success")
    public String success(@RequestParam("session_id") String sessionId) {
        Payment payment = paymentService.findBySessionId(sessionId);
        if (paymentService.isSessionPaid(sessionId)) {
            return "invalid payment";
        }
        payment.setStatus(Payment.Status.PAID);
        paymentService.update(payment);
        return "Your payment was successful!";
    }

    @GetMapping("/cancel")
    public String cancelPayment() {
        return "Payment was canceled! But this payment page active for 24 hours.";
    }

    @GetMapping("/{id}")
    public List<PaymentResponseDto> getUserPayments(@PathVariable Long id) {
        return paymentService.findByUserId(id).stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
