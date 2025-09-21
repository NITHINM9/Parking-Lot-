package com.example.parking.repositories;

import com.example.parking.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
      SELECT r FROM Reservation r
      WHERE r.slot.id = :slotId
        AND r.startTime < :end
        AND r.endTime > :start
    """)
    List<Reservation> findOverlappingReservations(@Param("slotId") Long slotId,
                                                  @Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);

    @Query("""
      SELECT COUNT(r) > 0 FROM Reservation r
      WHERE r.slot.id = :slotId
        AND r.startTime < :end
        AND r.endTime > :start
    """)
    boolean existsOverlapping(@Param("slotId") Long slotId,
                              @Param("start") LocalDateTime start,
                              @Param("end") LocalDateTime end);
}
