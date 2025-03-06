package edu.eci.cvds.Labtools.mapper;
import edu.eci.cvds.Labtools.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingMapperTest {
    User dummyUser = new BasicUser();
    Lab testLab = new Lab();
    BookingMapper mapper = new BookingMapper();

    @Test
    void toDTO_ShouldConvertBookingCorrectly() {
        Booking booking = new Booking() {};
        booking.setBookingId("1234567");
        booking.setDay(3);
        booking.setUser(dummyUser);
        booking.setLab(testLab);
        booking.setTimeLine(2);

        BookingDTO dto = mapper.toDTO(booking);

        assertEquals("1234567", dto.getBookingId());
        assertEquals(3, dto.getDay());
        assertEquals(dummyUser.getName(), dto.getUserName());
        assertEquals(testLab.getLabId(), dto.getLabName());
        assertEquals(2, dto.getTimeLine());
    }
}
