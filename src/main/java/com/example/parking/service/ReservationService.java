package com.example.parking.service;

import com.example.parking.domain.*;
import com.example.parking.exceptions.BusinessException;
import com.example.parking.repositories.*;
import com.example.parking.web.dto.ReservationRequest;
import com.example.parking.web.dto.ReservationResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final SlotRepository slotRepo;
    private final RateRepository rateRepo;

    public ReservationService(ReservationRepository reservationRepo,
                              SlotRepository slotRepo,
                              RateRepository rateRepo) {
        this.reservationRepo = reservationRepo;
        this.slotRepo = slotRepo;
        this.rateRepo = rateRepo;
    }

    @Transactional
    public ReservationResponse createReservation(ReservationRequest req) {
        if (req.getStartTime().isAfter(req.getEndTime()) || req.getStartTime().isEqual(req.getEndTime())) {
            throw new BusinessException("Start time must be before end time");
        }

        Duration duration = Duration.between(req.getStartTime(), req.getEndTime());
        if (duration.toHours() > 24) {
            throw new BusinessException("Reservation cannot exceed 24 hours");
        }

        // Find slot
        Slot slot = slotRepo.findById(req.getSlotId())
                .orElseThrow(() -> new BusinessException("Slot not found"));

        // Check overlap
        boolean hasOverlap = reservationRepo.existsOverlapping(slot.getId(), req.getStartTime(), req.getEndTime());
        if (hasOverlap) {
            throw new BusinessException("Slot already reserved in this time range");
        }

        // Rate lookup
        Rate rate = rateRepo.findByVehicleType(req.getVehicleType())
                .orElseThrow(() -> new BusinessException("Rate not configured for vehicle type"));

        // Calculate hours (round up partial)
        long minutes = duration.toMinutes();
        long hours = (minutes + 59) / 60; // round up
        BigDecimal fee = rate.getPricePerHour().multiply(BigDecimal.valueOf(hours));

        // Save reservation
        Reservation res = new Reservation();
        res.setSlot(slot);
        res.setVehicleType(req.getVehicleType());
        res.setVehicleNumber(req.getVehicleNumber());
        res.setStartTime(req.getStartTime());
        res.setEndTime(req.getEndTime());
        res.setFee(fee);

        Reservation saved = reservationRepo.save(res);

        return new ReservationResponse(
                saved.getId(),
                slot.getId(),
                saved.getVehicleType(),
                saved.getVehicleNumber(),
                saved.getStartTime(),
                saved.getEndTime(),
                saved.getFee()
        );
    }

    @Transactional(readOnly = true)
    public ReservationResponse getReservation(Long id) {
        Reservation res = reservationRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Reservation not found"));
        return new ReservationResponse(
                res.getId(), res.getSlot().getId(),
                res.getVehicleType(), res.getVehicleNumber(),
                res.getStartTime(), res.getEndTime(), res.getFee()
        );
    }

    @Transactional
    public void cancelReservation(Long id) {
        Reservation res = reservationRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Reservation not found"));
        reservationRepo.delete(res);
    }
}
