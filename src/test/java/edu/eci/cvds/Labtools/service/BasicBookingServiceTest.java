package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateBookingDTO;
import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoBookingRepository;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class BasicBookingServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MongoBookingRepository bookingRepository;

    @MockitoBean
    private MongoUserRepository userRepository;

    @MockitoBean
    private MongoLabRepository labRepository;

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
        mockUser.setRol(false);

        Lab mockLab = new Lab();
        mockLab.setLabId("lab123");
        mockLab.setName("testLab");

        Mockito.when(userRepository.findByName("testUser")).thenReturn(mockUser);
        Mockito.when(labRepository.findByName("testLab")).thenReturn(mockLab);

        mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"testUser\", \"labName\": \"testLab\", \"date\": \"2030-03-10 07:00:00\"}"))
                .andExpect(status().isCreated()) // Asegurar que la respuesta es 201 Created
                .andExpect(jsonPath("$.bookingId").exists()) // Verificar que se genera un ID de reserva
                .andExpect(jsonPath("$.date").value("2030-03-10 07:00:00"))
                .andExpect(jsonPath("$.lab.labId").value("lab123"))
                .andExpect(jsonPath("$.lab.name").value("testLab"))
                .andExpect(jsonPath("$.lab.isAvailable").exists()); // Verificar que el campo de disponibilidad está presente
    }

    @Test
    public void testCreateBookingWithInvalidUser() throws Exception {
        CreateBookingDTO createBookingDTO = new CreateBookingDTO();
        createBookingDTO.setUserName ("invalidUser");
        createBookingDTO.setLabName ("testLab");
        createBookingDTO.setDate("2025-03-10 7:00:00");

        Mockito.when(userRepository.findByName("invalidUser")).thenReturn(null);

        mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"invalidUser\", \"labName\": \"testLab\", \"date\": \"2025-03-10\"}"))
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
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"testUser\", \"labName\": \"invalidLab\", \"date\": \"2025-03-10\"}"))
                .andExpect(status().isBadRequest()); // Asegurar que devuelve 400
    }

}

