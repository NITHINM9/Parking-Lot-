package com.example.parking.repositories;

import com.example.parking.domain.Slot;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    @Query("""
    SELECT s FROM Slot s
    WHERE NOT EXISTS (
      SELECT r FROM Reservation r
      WHERE r.slot = s
        AND r.startTime < :end
        AND r.endTime > :start
    )
    """)
    List<Slot> findAvailableSlots(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
