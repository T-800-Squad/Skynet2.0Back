package edu.eci.cvds.Labtools.controller;

import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import edu.eci.cvds.Labtools.service.JwtService;
import edu.eci.cvds.Labtools.service.LabService;
import edu.eci.cvds.Labtools.service.UserService;
import edu.eci.cvds.Labtools.dto.CreateUserDTO;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.model.Lab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Mock
    private LabService labService;

    @Mock
    private JwtService jwtService;
    @Mock
    private MongoUserRepository userRepository;
    @Mock
    private MongoLabRepository mongoLabRepository;

    @InjectMocks
    private AdminController adminController;

    private String adminToken;
    private String nonAdminToken = "invalidToken";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        JwtService jwtService1 = new JwtService();
        adminToken = jwtService1.generateToken("testUser","Admin");
    }

    @Test
    void testCreateUserAsAdminReturnsCreatedUser() throws Exception {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("testUser");
        createUserDTO.setPassword("123");
        createUserDTO.setEmail("testUser@mail.escuelaing.edu.co");
        createUserDTO.setRole("Admin");
        User user = new BasicUser();
        user.setEmail("testUser@mail.escuelaing.edu.co");
        user.setPassword("password");
        user.setName("testUser");

        Mockito.when(userService.createUser(Mockito.any())).thenReturn(user);

        mockMvc.perform(post("/admin/user")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType("application/json")
                        .content("{\"username\": \"testUser\",\"password\": \"123\",\"email\": \"testUser@mail.escuelaing.edu.co\",  \"role\": \"Admin\"}"))
                .andExpect(status().isCreated());

    }
    @Test
    void testDeleteUser() throws Exception {

        doNothing().when(userService).deleteUser(Mockito.any());

        mockMvc.perform(delete("/admin/user")
                        .header("Authorization", "Bearer " + adminToken)
                        .param("userName", "testUser"))
                .andExpect(status().isOk());

    }

    @Test
    void testCreateUser_AsNonAdmin_ReturnsUnauthorized() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("testUser");
        createUserDTO.setPassword("password");
        createUserDTO.setEmail("testUser@mail.escuelaing.edu.co");
        createUserDTO.setRole("Admin");
        when(jwtService.validateUserIsAdmin(nonAdminToken)).thenReturn(false);

        ResponseEntity<User> response = adminController.createUser(createUserDTO, "Bearer " + nonAdminToken);

        assertEquals(401, response.getStatusCodeValue());
        assertNull(response.getBody());
    }


    @Test
    void testDeleteUser_AsNonAdmin_ReturnsUnauthorized() {
        String userName = "testUser";

        when(jwtService.validateUserIsAdmin(nonAdminToken)).thenReturn(false);

        ResponseEntity<String> response = adminController.deleteUser(userName, "Bearer " + nonAdminToken);

        assertEquals(401, response.getStatusCodeValue());
        assertNull(response.getBody());
    }



    @Test
    void testCreateLab_AsNonAdmin_ReturnsUnauthorized() {
        String labName = "Test Lab";

        when(jwtService.validateUserIsAdmin(nonAdminToken)).thenReturn(false);

        ResponseEntity<Lab> response = adminController.createLab(labName, "Bearer " + nonAdminToken);

        assertEquals(401, response.getStatusCodeValue());
        assertNull(response.getBody());
    }



    @Test
    void testDeleteLab_AsNonAdmin_ReturnsUnauthorized() {
        String labName = "Test Lab";

        when(jwtService.validateUserIsAdmin(nonAdminToken)).thenReturn(false);

        ResponseEntity<String> response = adminController.deleteLab(labName, "Bearer " + nonAdminToken);

        assertEquals(401, response.getStatusCodeValue());
        assertNull(response.getBody());
    }


}