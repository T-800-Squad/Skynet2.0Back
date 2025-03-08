package edu.eci.cvds.Labtools.mapper;

import edu.eci.cvds.Labtools.model.BasicUser;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.model.UserDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {
    private final UserMapper userMapper = new UserMapper();

    @Test
    void testToDTO() {
        User user = new BasicUser();
        user.setName("Ivan");
        user.setEmail("ivan.cubillos-v@mail.escuelaing.edu.co");
        user.setPassword("password123");
        user.setLogged(false);

        UserDTO userDTO = userMapper.toDTO(user);

        assertNotNull(userDTO);
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.isLogged(), userDTO.isLogged());
    }

    @Test
    void testToEntityAdmin() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("1001");
        userDTO.setName("Ivan");
        userDTO.setEmail("ivan.cubillos-v@mail.escuelaing.edu.co");
        userDTO.setPassword("password123");
        userDTO.setLogged(true);

        User user = userMapper.toEntity(userDTO);

        assertNotNull(user);
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.isLogged(), user.isLogged());
    }

    @Test
    void testToEntityBasicUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("1000");
        userDTO.setName("Yojhan");
        userDTO.setEmail("yojhan.toro-r@mail.escuelaing.edu.co");
        userDTO.setPassword("password456");
        userDTO.setLogged(true);

        User user = userMapper.toEntity(userDTO);

        assertNotNull(user);
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.isLogged(), user.isLogged());
    }

    @Test
    void testToDTOWithNull() {
        assertNull(userMapper.toDTO(null));
    }

    @Test
    void testToEntityWithNull() {
        assertNull(userMapper.toEntity(null));
    }
}

