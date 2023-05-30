package com.carsharing.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
