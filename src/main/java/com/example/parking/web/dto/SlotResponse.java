package com.example.parking.web.dto;

import com.example.parking.domain.VehicleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SlotResponse {
    @Schema(description = "Slot ID")
    private Long id;

    @Schema(description = "Slot name")
    private String name;

    @Schema(description = "Floor ID")
    private Long floorId;

    @Schema(description = "Vehicle type allowed in this slot")
    private VehicleType vehicleType;
}
