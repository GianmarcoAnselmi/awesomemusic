package it.adesso.awesomemusic.booking.repository;

import it.adesso.awesomemusic.booking.model.Booking;
import it.adesso.awesomemusic.booking.model.BookingStatus;
import it.adesso.awesomemusic.booking.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByCode(String code);

    List<Booking> findByStatusOrderByCreatedAtAsc(BookingStatus status);

    boolean existsByDateAndSlotAndStatus(LocalDate date, Slot slot, BookingStatus status);

}
