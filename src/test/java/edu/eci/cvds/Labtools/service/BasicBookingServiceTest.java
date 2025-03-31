package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.dto.DeleteBookingDTO;
import edu.eci.cvds.Labtools.model.*;
import edu.eci.cvds.Labtools.repository.MongoBookingRepository;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BasicBookingServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JwtService jwtService;

    @MockitoBean
    private MongoBookingRepository bookingRepository;

    @MockitoBean
    private MongoUserRepository userRepository;

    @MockitoBean
    private MongoLabRepository labRepository;

    private String token;

    @BeforeEach
    void setUp() {
        token = jwtService.generateToken("testUser","User");
    }


    @Test
    public void testCreateBookingWithValidData() throws Exception {
        // Crear datos de prueba
        CreateBookingDTO createBookingDTO = new CreateBookingDTO();
        createBookingDTO.setUserName ("testUser");
        createBookingDTO.setLabName("testLab");
        createBookingDTO.setDate("2030-03-10 07:00:00");

        User mockUser = new BasicUser();
        mockUser.setUserId("user123");
        mockUser.setName("testUser");
        mockUser.setEmail("testUser@example.com");
        mockUser.setPassword("password123");
        mockUser.setLogged(true);
        mockUser.setRol("user");

        Lab mockLab = new Lab();
        mockLab.setLabId("lab123");
        mockLab.setName("testLab");

        Mockito.when(userRepository.findByName("testUser")).thenReturn(mockUser);
        Mockito.when(labRepository.findByName("testLab")).thenReturn(mockLab);

        mockMvc.perform(post("/booking")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"testUser\", \"labName\": \"testLab\", \"date\": \"2030-03-10 07:00:00\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookingId").exists())
                .andExpect(jsonPath("$.date").value("2030-03-10 07:00:00"))
                .andExpect(jsonPath("$.lab.labId").value("lab123"))
                .andExpect(jsonPath("$.lab.name").value("testLab"))
                .andExpect(jsonPath("$.lab.isAvailable").exists());
    }

    @Test
    public void testCreateBookingWithInvalidUser() throws Exception {
        CreateBookingDTO createBookingDTO = new CreateBookingDTO();
        createBookingDTO.setUserName ("invalidUser");
        createBookingDTO.setLabName ("testLab");
        createBookingDTO.setDate("2025-03-10 7:00:00");

        Mockito.when(userRepository.findByName("testUser")).thenReturn(null);

        mockMvc.perform(post("/booking")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"testUser\", \"labName\": \"testLab\", \"date\": \"2025-03-10\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateBookingWithInvalidLab() throws Exception {
        CreateBookingDTO createBookingDTO = new CreateBookingDTO();
        createBookingDTO.setUserName("testUser");
        createBookingDTO.setLabName("invalidLab");
        createBookingDTO.setDate("2025-03- 7:00:00");

        User mockUser = new BasicUser();
        mockUser.setName("testUser");

        // Simular que el usuario sí existe
        Mockito.when(userRepository.findByName("testUser")).thenReturn(mockUser);
        // Simular que el laboratorio no existe
        Mockito.when(labRepository.findByName("invalidLab")).thenReturn(null);

        // Realizar la petición al endpoint correcto
        mockMvc.perform(post("/booking") // <-- Aquí se cambia a "/booking/add"
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"testUser\", \"labName\": \"invalidLab\", \"date\": \"2025-03-10\"}"))
                .andExpect(status().isBadRequest()); // Asegurar que devuelve 400
    }
    @Test
    public void testDeleteBooking() {
        DeleteBookingDTO deleteBookingDTO = new DeleteBookingDTO();
        deleteBookingDTO.setUserName("testUser");
        deleteBookingDTO.setBookingId("123");

        Lab mockLab = new Lab();
        mockLab.setLabId("lab123");
        mockLab.setName("testLab");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2029-03-10 07:00:00",formatter);
        mockLab.setIsAvailable(dateTime);

        Booking mockBooking = new Booking();
        mockBooking.setLab(mockLab);
        mockBooking.setBookingId(deleteBookingDTO.getBookingId());
        mockBooking.setDate("2029-03-10 07:00:00");

        User mockUser = new BasicUser();
        mockUser.setUserId("user123");
        mockUser.setName("testUser");
        mockUser.addBooking(mockBooking);

        Optional<Booking> bookingOptional = Optional.of(mockBooking);
        Mockito.when(bookingRepository.findById("123")).thenReturn(bookingOptional);
        Mockito.when(userRepository.findByName("testUser")).thenReturn(mockUser);
        Mockito.when(labRepository.findByName("testLab")).thenReturn(mockLab);
        HashMap<LocalDateTime,Boolean> map = mockLab.getIsAvailable();
        List<Booking> list = mockUser.getBookings();

        bookingService.deleteBooking(deleteBookingDTO);

        assertFalse(map.containsKey(dateTime));
        assertFalse(list.contains(mockBooking));
    }

    @Test
    public void testNotDeleteBookingWithInvalidUser() {
        try{
            DeleteBookingDTO deleteBookingDTO = new DeleteBookingDTO();
            deleteBookingDTO.setUserName("testUser");
            deleteBookingDTO.setBookingId("123");

            Lab mockLab = new Lab();
            mockLab.setLabId("lab123");
            mockLab.setName("testLab");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse("2029-03-10 07:00:00",formatter);
            mockLab.setIsAvailable(dateTime);

            Booking mockBooking = new Booking();
            mockBooking.setLab(mockLab);
            mockBooking.setBookingId(deleteBookingDTO.getBookingId());
            mockBooking.setDate("2029-03-10 07:00:00");
            Optional<Booking> bookingOptional = Optional.of(mockBooking);
            Mockito.when(bookingRepository.findById("123")).thenReturn(bookingOptional);
            Mockito.when(userRepository.findByName("testUser")).thenReturn(null);
            Mockito.when(labRepository.findByName("testLab")).thenReturn(mockLab);
            bookingService.deleteBooking(deleteBookingDTO);
        }catch (IllegalArgumentException e){
            assertEquals(e.getMessage(),"User not found");
        }
    }

    @Test
    public void testNotDeleteBookingThatNotExist() {
        try {
            DeleteBookingDTO deleteBookingDTO = new DeleteBookingDTO();
            deleteBookingDTO.setUserName("testUser");
            deleteBookingDTO.setBookingId("123");

            Lab mockLab = new Lab();
            mockLab.setLabId("lab123");
            mockLab.setName("testLab");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse("2029-03-10 07:00:00",formatter);
            mockLab.setIsAvailable(dateTime);


            User mockUser = new BasicUser();
            mockUser.setUserId("user123");
            mockUser.setName("testUser");


            Optional<Booking> bookingOptional = Optional.empty();
            Mockito.when(bookingRepository.findById("123")).thenReturn(bookingOptional);
            Mockito.when(userRepository.findByName("testUser")).thenReturn(mockUser);


            bookingService.deleteBooking(deleteBookingDTO);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Booking not found");
        }
    }

}

