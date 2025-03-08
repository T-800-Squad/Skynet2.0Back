package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.service.BasicQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QueryController.class)
public class QueryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BasicQueryService basicQueryService;

    @Test
    public void shouldReturnUserBookingsIfBasicUserExists() throws Exception {
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

        Booking[] mockBookings = new Booking[] {booking1, booking2};

        when(basicQueryService.queryUserBookings(userId)).thenReturn(mockBookings);

        mockMvc.perform(get("/query")
                        .param("userId", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].bookingId").value("b1"))
                .andExpect(jsonPath("$[1].bookingId").value("b2"));
    }
}
