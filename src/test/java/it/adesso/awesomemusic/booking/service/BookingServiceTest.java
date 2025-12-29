package it.adesso.awesomemusic.booking.service;

import it.adesso.awesomemusic.booking.dto.CreateBookingRequest;
import it.adesso.awesomemusic.booking.model.Slot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Test
    void createBooking_shouldCreateBookingAndReturnCode() {
        CreateBookingRequest request = new CreateBookingRequest(
                LocalDate.now().plusDays(1),
                Slot.MATTINA,
                "Mario Rossi",
                "mario.rossi@email.com"
        );

        var response = bookingService.create(request);

        assertThat(response).isNotNull();
        assertThat(response.code()).isNotBlank();
        assertThat(response.date()).isEqualTo(request.date());
        assertThat(response.slot()).isEqualTo(request.slot());
    }
}
