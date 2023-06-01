package com.carsharing.repository;

import com.carsharing.model.Rental;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findRentalByUserId(Long id);

    List<Rental> findRentalByActiveIsTrueAndUserId(Long id);
}
