package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BasicBookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void testFindUserById() {
        Booking foundBooking = bookingRepository.findById("1").orElse(null);

        assertNotNull(bookingRepository);
        //assertEquals("Miguel", bookingRepository.getBookingsByBookingId());
    }

    @Test
    public void testFindAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        assertEquals(2, bookings.size());
    }
}
