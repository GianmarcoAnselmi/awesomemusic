package it.adesso.awesomemusic.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "bookings", indexes = {
        @Index(name = "idx_booking_code", columnList = "code", unique = true),
        @Index(name = "idx_booking_status_created", columnList = "status, createdAt")
})
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 32)
    private String code;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private Slot slot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private BookingStatus status;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 120)
    private String email;

    @Column(nullable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @Column(length = 255)
    private String rejectReason;

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }

    // metodi “di dominio” (meglio dei setter globali)
    public void markPending(String code) {
        this.code = code;
        this.status = BookingStatus.PENDING;
    }

    public void approve() {
        this.status = BookingStatus.APPROVED;
        this.rejectReason = null;
    }

    public void reject(String reason) {
        this.status = BookingStatus.REJECTED;
        this.rejectReason = reason;
    }

    // Factory method (ti evita setter sparsi)
    public static Booking create(LocalDate date, Slot slot, String name, String email, String code) {
        Booking b = new Booking();
        b.date = date;
        b.slot = slot;
        b.name = name;
        b.email = email;
        b.markPending(code);
        return b;
    }
}
