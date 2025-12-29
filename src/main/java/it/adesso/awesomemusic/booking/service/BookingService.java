package it.adesso.awesomemusic.booking.service;

import it.adesso.awesomemusic.booking.dto.BookingResponse;
import it.adesso.awesomemusic.booking.dto.CreateBookingRequest;
import it.adesso.awesomemusic.booking.model.Booking;
import it.adesso.awesomemusic.booking.model.BookingStatus;
import it.adesso.awesomemusic.booking.repository.BookingRepository;
import it.adesso.awesomemusic.common.exception.ConflictException;
import it.adesso.awesomemusic.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    @Transactional
    public BookingResponse create(CreateBookingRequest req) {
        String code = generateCode();
        Booking booking = Booking.create(req.date(), req.slot(), req.name(), req.email(), code);
        Booking saved = bookingRepository.save(booking);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public BookingResponse getByCode(String code) {
        Booking booking = bookingRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Booking not found for code: " + code));
        return toResponse(booking);
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getPendingQueue() {
        return bookingRepository.findByStatusOrderByCreatedAtAsc(BookingStatus.PENDING)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public BookingResponse approve(String code) {
        Booking booking = bookingRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Booking not found for code: " + code));

        // regola: non puoi avere 2 APPROVED per stesso giorno+slot
        boolean conflict = bookingRepository.existsByDateAndSlotAndStatus(
                booking.getDate(),
                booking.getSlot(),
                BookingStatus.APPROVED
        );

        if (conflict) {
            throw new ConflictException("Slot already approved for date=" + booking.getDate() + " slot=" + booking.getSlot());
        }

        booking.approve();
        return toResponse(booking);
    }

    @Transactional
    public BookingResponse reject(String code, String reason) {
        Booking booking = bookingRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Booking not found for code: " + code));

        booking.reject(reason);
        return toResponse(booking);
    }

    private String generateCode() {
        return "RB-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

    private BookingResponse toResponse(Booking b) {
        return new BookingResponse(
                b.getCode(),
                b.getDate(),
                b.getSlot(),
                b.getStatus(),
                b.getRejectReason(),
                b.getCreatedAt()
        );
    }

}
