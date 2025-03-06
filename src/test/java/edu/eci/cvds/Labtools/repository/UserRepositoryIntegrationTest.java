package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserById() {
        User foundUser = userRepository.findById("67c9c1d729561adf2afa4214").orElse(null);

        assertNotNull(foundUser);
        assertEquals("Ivan", foundUser.getName());
    }

    @Test
    public void testFindAllUsers() {
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
    }
}
