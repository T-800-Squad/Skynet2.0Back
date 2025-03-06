package edu.eci.cvds.Labtools.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.Labtools.repository.UserRepository;

@SpringBootTest
public class BasicEmailVerificationServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BasicEmailVerificationService basicEmailVerificationService;

    @Test
    public void testGetUserById() {
        
    }
}
