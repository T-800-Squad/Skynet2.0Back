package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.dto.CreateUserDTO;
import edu.eci.cvds.Labtools.model.Admin;
import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BasicUserServiceTest {

    @Mock
    private MongoUserRepository userRepository;

    @Mock
    private HashService hashService;

    @InjectMocks
    private BasicUserService userService;

    private CreateUserDTO createUserDTO;

    @BeforeEach
    void setUp() {
        createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("testUser");
        createUserDTO.setPassword("123");
        createUserDTO.setEmail("testUser@mail.escuelaing.edu.co");
        createUserDTO.setRole("Admin");
    }

    @Test
    void testCreateUser_AsAdmin_Success() {
        when(userRepository.findByName(createUserDTO.getUsername())).thenReturn(null);
        when(hashService.passwordHashsing(createUserDTO.getPassword())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User createdUser = userService.createUser(createUserDTO);

        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getName());
        assertEquals("hashedPassword", createdUser.getPassword());
        assertTrue(createdUser instanceof Admin);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_UserAlreadyExists_ThrowsException() {
        when(userRepository.findByName(createUserDTO.getUsername())).thenReturn(new BasicUser());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(createUserDTO);
        });

        assertEquals("User already exists", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testCreateUser_InvalidRole_ThrowsException() {
        createUserDTO.setRole("InvalidRole");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(createUserDTO);
        });

        assertEquals("Invalid role", exception.getMessage());
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.findByName("testUser")).thenReturn(new BasicUser());
        doNothing().when(userRepository).deleteByName("testUser");

        assertDoesNotThrow(() -> userService.deleteUser("testUser"));
        verify(userRepository, times(1)).deleteByName("testUser");
    }

    @Test
    void testDeleteUser_UserNotFound_ThrowsException() {
        when(userRepository.findByName("testUser")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser("testUser");
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, never()).deleteByName(anyString());
    }
}
