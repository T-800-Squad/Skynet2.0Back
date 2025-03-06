package edu.eci.cvds.Labtools.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BasicBookingServiceTest{
    @Test
    public void testCheckAvailability() {
        BasicBookingService service = new BasicBookingService();

        boolean[][] availabilityInitial = service.checkAvailability();

        //service.createBooking();

        //assertNull(availability);
    }
}
