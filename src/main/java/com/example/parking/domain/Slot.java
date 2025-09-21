package com.example.parking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "slots")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    @Version
    private Long version;

    private String name;
    private VehicleType type;


    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public VehicleType getVehicleType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }
}
