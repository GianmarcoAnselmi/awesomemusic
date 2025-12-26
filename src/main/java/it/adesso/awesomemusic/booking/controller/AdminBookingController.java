package it.adesso.awesomemusic.booking.controller;

import it.adesso.awesomemusic.booking.dto.BookingResponse;
import it.adesso.awesomemusic.booking.dto.RejectBookingRequest;
import it.adesso.awesomemusic.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/bookings")
public class AdminBookingController {

    private final BookingService bookingService;

    @GetMapping("/pending")
    public List<BookingResponse> pendingQueue() {
        return bookingService.getPendingQueue();
    }

    @PutMapping("/{code}/approve")
    public BookingResponse approve(@PathVariable String code) {
        return bookingService.approve(code);
    }

    @PutMapping("/{code}/reject")
    public BookingResponse reject(@PathVariable String code, @Valid @RequestBody RejectBookingRequest req) {
        return bookingService.reject(code, req.reason());
    }
}
