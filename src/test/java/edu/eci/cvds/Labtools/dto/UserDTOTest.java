package edu.eci.cvds.Labtools.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDTOTest {
    @Test
    public void testUserDTOSettersAndGetters() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John");
        userDTO.setRol("Admin");
        userDTO.setToken("213");
        assertEquals(userDTO.getName(), "John");
        assertEquals(userDTO.getRol(), "Admin");
        assertEquals(userDTO.getToken(), "213");
    }
}
