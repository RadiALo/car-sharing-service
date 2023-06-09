package com.carsharing.dto.response;

import com.carsharing.model.Payment;
import com.carsharing.model.Rental;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentResponseDto {
    private Long id;
    private Payment.Status status;
    private Payment.Type type;
    private Rental rental;
    private String sessionUrl;
    private String sessionId;
    private BigDecimal amount;
}
