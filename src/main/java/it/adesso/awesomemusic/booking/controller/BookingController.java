package it.adesso.awesomemusic.booking.controller;

import it.adesso.awesomemusic.booking.dto.BookingResponse;
import it.adesso.awesomemusic.booking.dto.CreateBookingRequest;
import it.adesso.awesomemusic.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(@Valid @RequestBody CreateBookingRequest request) {
        return bookingService.create(request);
    }

    @GetMapping("/{code}")
    public BookingResponse getBooking(@PathVariable String code) {
        return bookingService.getByCode(code);
    }

}
