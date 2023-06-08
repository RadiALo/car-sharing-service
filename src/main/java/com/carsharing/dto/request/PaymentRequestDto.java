package com.carsharing.dto.request;

import com.carsharing.model.Payment;
import java.math.BigDecimal;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequestDto {
    @Positive
    private Long rentalId;
    private Payment.Type type;
    private String sessionId;
    private String sessionUrl;
    private Payment.Status status;
    @Positive
    private BigDecimal amount;
}
