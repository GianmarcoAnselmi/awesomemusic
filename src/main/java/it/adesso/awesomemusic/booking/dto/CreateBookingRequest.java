package it.adesso.awesomemusic.booking.dto;

import it.adesso.awesomemusic.booking.model.Slot;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateBookingRequest(
        @NotNull LocalDate date,
        @NotNull Slot slot,
        @NotBlank String name,
        @NotBlank @Email String email
) {}
