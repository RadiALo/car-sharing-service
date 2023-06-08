package com.carsharing.dto.request;

import com.carsharing.model.Payment;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long rentalId;
    private Payment.Type type;
    private String sessionId;
    private String sessionUrl;
    private Payment.Status status;
    private BigDecimal amount;
}
