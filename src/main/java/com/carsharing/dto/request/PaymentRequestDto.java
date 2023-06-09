package com.carsharing.dto.request;

import com.carsharing.model.Payment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequestDto {
    @Schema(example = "1")
    @Positive
    private Long rentalId;
    @Schema(example = "PAYMENT")
    @Enumerated(EnumType.STRING)
    private Payment.Type type;
}
