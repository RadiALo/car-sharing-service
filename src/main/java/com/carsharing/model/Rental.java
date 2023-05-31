package com.carsharing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date rentalDate;
    private Date returnDate;
    private Date actualReturnDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Car car;
    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private User user;

    @Override
    public String toString() {
        return "Rental{"
                + "id=" + id
                + ", rentalDate=" + rentalDate
                + ", returnDate=" + returnDate
                + ", actualReturnDate=" + returnDate + '}';
    }
}
