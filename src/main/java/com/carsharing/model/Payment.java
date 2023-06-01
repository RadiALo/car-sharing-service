package com.carsharing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    @EqualsAndHashCode.Exclude
    private Rental rental;
    @Column(name = "session_url")
    private String sessionUrl;
    @Column(name = "session_id")
    private String sessionId;
    private BigDecimal amount;

    @Override
    public String toString() {
        return "Payment{"
                + "status=" + status
                + ", sessionUrl=" + sessionUrl
                + ", sessionId=" + sessionId
                + ", amount=" + amount + "}";
    }
}
