package com.example.parking.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rates", uniqueConstraints = @UniqueConstraint(columnNames = {"vehicle_type"}))
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "price_per_hour", nullable = false, scale = 2, precision = 10)
    private BigDecimal pricePerHour;



    public Long getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
