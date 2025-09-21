package com.example.parking.repositories;

import com.example.parking.domain.Rate;
import com.example.parking.domain.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByVehicleType(VehicleType vehicleType);
}
