package com.example.parking.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SlotRequest {
    @NotNull
    private Long floorId;

    @Size(min = 1, max = 20)
    private String code;

    public Long getFloorId() { return floorId; }
    public void setFloorId(Long floorId) { this.floorId = floorId; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
