package com.example.parking.web.dto;

import jakarta.validation.constraints.NotBlank;

public class FloorRequest {
    @NotBlank
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
