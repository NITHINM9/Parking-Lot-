package com.example.parking.web.dto;

import com.example.parking.domain.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationResponse {
    private Long id;
    private Long slotId;
    private VehicleType vehicleType;
    private String vehicleNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal fee;

    public ReservationResponse(Long id, Long slotId, VehicleType vehicleType, String vehicleNumber,
                               LocalDateTime startTime, LocalDateTime endTime, BigDecimal fee) {
        this.id = id;
        this.slotId = slotId;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fee = fee;
    }

    public Long getId() { return id; }
    public Long getSlotId() { return slotId; }
    public VehicleType getVehicleType() { return vehicleType; }
    public String getVehicleNumber() { return vehicleNumber; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public BigDecimal getFee() { return fee; }
}
