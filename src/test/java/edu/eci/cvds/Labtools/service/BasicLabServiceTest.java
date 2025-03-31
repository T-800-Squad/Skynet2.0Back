package edu.eci.cvds.Labtools.service;

import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.repository.MongoLabRepository;
import edu.eci.cvds.Labtools.service.BasicLabService;
import edu.eci.cvds.Labtools.service.LabService;
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
class BasicLabServiceTest {

    @Mock
    private MongoLabRepository labRepository;

    @InjectMocks
    private BasicLabService labService;

    private Lab lab;

    @BeforeEach
    void setUp() {
        lab = new Lab();
        lab.setName("Test Lab");
    }

    @Test
    void testCreateLab_SuccessfullyCreatesLab() {
        when(labRepository.findByName(lab.getName())).thenReturn(null);
        when(labRepository.save(any(Lab.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Lab createdLab = labService.createLab(lab.getName());

        assertNotNull(createdLab);
        assertEquals("Test Lab", createdLab.getName());
        verify(labRepository, times(1)).save(any(Lab.class));
    }

    @Test
    void testCreateLab_LabAlreadyExists_ThrowsException() {
        when(labRepository.findByName(lab.getName())).thenReturn(lab);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> labService.createLab(lab.getName()));

        assertEquals("Lab already exists", exception.getMessage());
        verify(labRepository, never()).save(any(Lab.class));
    }

    @Test
    void testDeleteLab_LabExists_DeletesSuccessfully() {
        when(labRepository.findByName(lab.getName())).thenReturn(lab);

        labService.deleteLab(lab.getName());

        verify(labRepository, times(1)).deleteByName(lab.getName());
    }

    @Test
    void testDeleteLab_LabNotExists_ThrowsException() {
        when(labRepository.findByName("NonExistent Lab")).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> labService.deleteLab("NonExistent Lab"));

        assertEquals("Lab no exists", exception.getMessage());
        verify(labRepository, never()).deleteByName(anyString());
    }
}
