package edu.eci.cvds.Labtools.controller;



import edu.eci.cvds.Labtools.dto.BookingDTO;
import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.User;

import edu.eci.cvds.Labtools.service.JwtService;
import edu.eci.cvds.Labtools.service.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;



import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QueryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtService jwtService;
    @MockitoBean
    private QueryService queryService;
    private String token;

    @BeforeEach
    void setUp() {
        token = jwtService.generateToken("user","User");
    }

    @Test
    void testQueryAllTheBookingsIfTheUserHaveBookings() throws Exception {
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId("1");
        bookingDTO.setDate("14/03.2025 7:00");
        bookingDTO.setLabName("lab");
        bookingDTOS.add(bookingDTO);
        Mockito.when(queryService.findBookingsByName(Mockito.anyString())).thenReturn(bookingDTOS);
        mockMvc.perform(get("/query")
                        .header("Authorization", "Bearer " + token)
                .param("userName","user"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"bookingId\": \"1\"" +
                        ",\"labName\": \"lab\"" +
                        ",\"date\": \"14/03.2025 7:00\" }]"));
    }

    @Test
    void testQueryAllTheBookingsIfTheUserNotHaveBookings() throws Exception {
        User user = new BasicUser();
        user.setName("user");
        Mockito.when(queryService.findBookingsByName(Mockito.anyString())).thenReturn(null);
        mockMvc.perform(get("/query")
                        .header("Authorization", "Bearer " + token)
                        .param("userName","user"))
                .andExpect(status().isOk());
    }
    @Test
    void testQueryLabsInAnyDate() throws Exception {
        List<String> labs = new ArrayList<>();
        labs.add("1");
        labs.add("2");
        Mockito.when(queryService.checkAvailability(Mockito.anyString())).thenReturn(labs);
        mockMvc.perform(get("/query/lab")
                        .header("Authorization", "Bearer " + token)
                .param("date","2025-10-10 07:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"1\",\"2\"]"));
    }
}
