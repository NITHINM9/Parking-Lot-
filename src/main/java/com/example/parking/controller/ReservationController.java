package com.example.parking.controller;

import com.example.parking.service.ReservationService;
import com.example.parking.web.dto.ReservationRequest;
import com.example.parking.web.dto.ReservationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservation API", description = "Create, fetch, and cancel parking reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(summary = "Create a new reservation",
            description = "Creates a reservation for a vehicle in a specific slot. Validates time range, slot availability, and calculates the fee.")
    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request) {
        ReservationResponse res = reservationService.createReservation(request);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Get reservation by ID",
            description = "Fetches reservation details by its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long id) {
        ReservationResponse res = reservationService.getReservation(id);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Cancel reservation",
            description = "Cancels an existing reservation by ID. Frees up the slot for future bookings.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
