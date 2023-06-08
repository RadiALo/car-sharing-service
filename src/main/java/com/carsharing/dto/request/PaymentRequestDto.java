package com.carsharing.dto.request;

import com.carsharing.model.Payment;
import java.math.BigDecimal;
import java.net.URL;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequestDto {
    @Positive
    private Long rentalId;
    private Payment.Type type;
    private String sessionId;
    private URL sessionUrl;
    private Payment.Status status;
    @Positive
    private BigDecimal amount;
}
