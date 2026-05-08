package it.awesomemusic.booking.dto;

import it.awesomemusic.booking.model.BookingStatus;
import it.awesomemusic.booking.model.Slot;

import java.time.Instant;
import java.time.LocalDate;

public record BookingResponse(
        String code,
        LocalDate date,
        Slot slot,
        BookingStatus status,
        String rejectReason,
        Instant createdAt
) {}
