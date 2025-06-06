package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.LabToolsException;
import edu.eci.cvds.Labtools.dto.UserDTO;
import edu.eci.cvds.Labtools.dto.UserRegisterDTO;
import edu.eci.cvds.Labtools.model.Admin;
import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.repository.MongoUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest

public class BasicLogServiceTest{
    @MockitoBean
    private MongoUserRepository mongoUserRepository;

    @Autowired
    private HashService hashService;

    @Autowired
    private LogService logService;

    @Autowired
    private JwtService jwtService;

    @Test
    public void testLogUserThatExistInDataBase () {
        try {
            UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
            userRegisterDTO.setEmail("test@mail.escuelaing.edu.co");
            userRegisterDTO.setPassword("123");
            UserDTO mockUserDTO = new UserDTO();
            mockUserDTO.setRol("Admin");
            mockUserDTO.setName("test");
            User user = new BasicUser();
            user.setName("test");
            user.setRol("Admin");
            user.setPassword(hashService.passwordHashsing("123"));

            Mockito.when(mongoUserRepository.findByEmail("test@mail.escuelaing.edu.co")).thenReturn(Optional.of(user));
            UserDTO token = logService.userLog(userRegisterDTO);
            assertEquals("test", user.getName());
            assertEquals("Admin", user.getRol());

        } catch (LabToolsException e) {
            fail();
        }
    }

    @Test
    public void testLogUserThatNotExistInDataBase () {

        try {
            UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
            userRegisterDTO.setEmail("test@mail.escuelaing.edu.co");
            userRegisterDTO.setPassword("123");
            logService.userLog(userRegisterDTO);
        } catch (LabToolsException e) {
            assertEquals("User with that email does not exist.", e.getMessage());
        }

    }

    @Test
    public void testLogUserThatPasswordIsWrong () {

        try {
            UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
            userRegisterDTO.setEmail("test@mail.escuelaing.edu.co");
            userRegisterDTO.setPassword("123");
            String expectedPassword = hashService.passwordHashsing("123");
            User user = new Admin();
            user.setPassword(expectedPassword);
            user.setEmail("test@mail.escuelaing.edu.co");

            Mockito.when(mongoUserRepository.findByEmail("test@mail.escuelaing.edu.co")).thenReturn(Optional.of(user));
            logService.userLog(userRegisterDTO);
        } catch (LabToolsException e) {
            assertEquals("User with that email does not exist.", e.getMessage());
        }

    }
}
