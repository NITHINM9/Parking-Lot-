package com.example.parking.controller;

import com.example.parking.domain.Slot;
import com.example.parking.repositories.SlotRepository;
import com.example.parking.web.dto.SlotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/slots")
@Tag(name = "Slot API", description = "Manage parking slots and check availability")
public class SlotController {

    private final SlotRepository slotRepository;

    public SlotController(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Operation(summary = "Get all slots",
            description = "Returns a list of all parking slots in the system")
    @GetMapping
    public List<SlotResponse> getAllSlots() {
        return slotRepository.findAll()
                .stream()
                .map(s -> {
                    SlotResponse dto = new SlotResponse();
                    dto.setId(s.getId());
                    dto.setName(s.getName());
                    dto.setFloorId(s.getFloor().getId());
                    dto.setVehicleType(s.getVehicleType());
                    return dto;
                })
                .toList();
    }


    @Operation(summary = "Create a new slot",
            description = "Creates a new parking slot with a name, floor, and vehicle type")
    @PostMapping
    public Slot createSlot(@RequestBody Slot slot) {
        if (slot.getFloor() == null || slot.getFloor().getId() == null) {
            throw new IllegalArgumentException("Floor is required for creating a Slot");
        }
        return slotRepository.save(slot);
    }

    @Operation(summary = "Check available slots",
            description = "Returns a list of slots that are available between the given start and end times")
    @GetMapping("/available")
    public List<Slot> getAvailableSlots(
            @Parameter(description = "Start time in format yyyy-MM-ddTHH:mm")
            @RequestParam LocalDateTime start,

            @Parameter(description = "End time in format yyyy-MM-ddTHH:mm")
            @RequestParam LocalDateTime end,

            @Parameter(description = "Maximum number of slots to return (pagination)")
            @RequestParam(defaultValue = "10") int size
    ) {
        return slotRepository.findAvailableSlots(start, end, PageRequest.of(0, size));
    }
}
