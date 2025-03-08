package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoBookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasicQueryServiceTest {
    @Mock
    private MongoBookingRepository mongoBookingRepository;

    @InjectMocks
    private BasicQueryService basicQueryService;

    @Test
    public void shouldReturnBookingsIfBasicUserHasBookings(){
        String userId = "123456789";
        User user = new BasicUser();
        user.setId(userId);

        Lab lab = new Lab();
        lab.setLabId("Plataformas");

        Booking booking1 = new Booking();
        booking1.setBookingId("b1");
        booking1.setDate(LocalDateTime.now());
        booking1.setUser(user);
        booking1.setLab(lab);

        Booking booking2 = new Booking();
        booking2.setBookingId("b2");
        booking2.setDate(LocalDateTime.now());
        booking2.setUser(user);
        booking2.setLab(lab);

        List<Booking> mockBookings = Arrays.asList(booking1, booking2);

        when(mongoBookingRepository.findByUserId(userId)).thenReturn(mockBookings);

        Booking[] result = basicQueryService.queryUserBookings(userId);

        assertEquals(2, result.length);
        assertEquals("b1", result[0].getBookingId());
        assertEquals("b2", result[1].getBookingId());
    }

    @Test
    public void shouldReturnAllBookingsForAdmin(){
        String userId = "123456789";
        User user = new BasicUser();
        user.setId(userId);

        Lab lab = new Lab();
        lab.setLabId("Plataformas");

        Booking booking1 = new Booking();
        booking1.setBookingId("b1");
        booking1.setDate(LocalDateTime.now());
        booking1.setUser(user);
        booking1.setLab(lab);

        Booking booking2 = new Booking();
        booking2.setBookingId("b2");
        booking2.setDate(LocalDateTime.now());
        booking2.setUser(user);
        booking2.setLab(lab);

        List<Booking> mockBookings = Arrays.asList(booking1, booking2);

        when(mongoBookingRepository.findAll()).thenReturn(mockBookings);

        List<Booking> result = basicQueryService.getAllBookings();

        assertEquals(2, result.size());
        assertEquals("b1", result.get(0).getBookingId());
        assertEquals("b2", result.get(1).getBookingId());
    }
}
