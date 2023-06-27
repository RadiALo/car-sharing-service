package com.carsharing.service.mapper;

import com.carsharing.dto.request.PaymentRequestDto;
import com.carsharing.dto.response.PaymentResponseDto;
import com.carsharing.model.Payment;
import com.carsharing.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentMapper implements DtoMapper<Payment, PaymentRequestDto, PaymentResponseDto> {
    private final RentalService rentalService;

    @Override
    public PaymentResponseDto toDto(Payment model) {
        PaymentResponseDto responseDto = new PaymentResponseDto();
        responseDto.setAmount(model.getAmount());
        responseDto.setRental(model.getRental());
        responseDto.setType(model.getType());
        responseDto.setId(model.getId());
        responseDto.setStatus(model.getStatus());
        responseDto.setSessionId(model.getSessionId());
        responseDto.setSessionUrl(model.getSessionUrl());
        return responseDto;
    }

    @Override
    public Payment toModel(PaymentRequestDto requestDto) {
        Payment payment = new Payment();
        payment.setRental(rentalService.get(requestDto.getRentalId()));
        payment.setType(requestDto.getType());
        return payment;
    }
}
