package edu.eci.cvds.Labtools.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LabTest {
    @Test
    public void testAddANewDate() {
        Lab lab = new Lab();
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1) ;
        lab.setIsAvailable(tomorrow);
        assertTrue(lab.getIsAvailable().get(tomorrow));
    }

    @Test
    public void testAddANewDateThatAlreadyExists() {
        Lab lab = new Lab();
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1) ;
        lab.setIsAvailable(tomorrow);
        assertTrue(lab.getIsAvailable().get(tomorrow));
        try {
            lab.setIsAvailable(tomorrow);
        } catch (IllegalArgumentException e) {
            assertEquals("this lab already has a booking on this date", e.getMessage());
        }

    }
    @Test
    public void testRemoveADateThatDoesNotExist() {
        Lab lab = new Lab();
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1) ;
        try {
            lab.deleteIsAvailable(tomorrow);
        } catch (IllegalArgumentException e) {
            assertEquals("this lab doesn't have a booking on this date", e.getMessage());
        }
    }
}
