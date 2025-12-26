package it.adesso.awesomemusic.booking.dto;

import jakarta.validation.constraints.NotBlank;

public record RejectBookingRequest(
        @NotBlank String reason
) {}
