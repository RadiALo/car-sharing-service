package com.carsharing.repository;

import com.carsharing.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> getRentalsByUserId(Long id);
}
