package com.carsharing.dto.request;

import com.carsharing.model.Payment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequestDto {
    @Positive
    private Long rentalId;
    @Enumerated(EnumType.STRING)
    private Payment.Type type;
    private String sessionId;
    private String sessionUrl;
    @Enumerated(EnumType.STRING)
    private Payment.Status status;
    @Positive
    private BigDecimal amount;
}
