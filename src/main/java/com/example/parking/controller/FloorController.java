package com.example.parking.controller;

import com.example.parking.domain.Floor;
import com.example.parking.repositories.FloorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floors")
@Tag(name = "Floor API", description = "Manage floors in the parking lot")
public class FloorController {

    private final FloorRepository floorRepository;

    public FloorController(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @GetMapping
    @Operation(summary = "Get all floors")
    public List<Floor> getAllFloors() {
        return floorRepository.findAll();
    }

    @Operation(summary = "Create a new floor")
    @PostMapping
    public ResponseEntity<Floor> createFloor(@RequestBody Floor floor) {
        Floor saved = floorRepository.save(floor);
        return ResponseEntity.ok(saved);
    }
}
