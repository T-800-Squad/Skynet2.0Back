package edu.eci.cvds.Labtools.mapper;

import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.LabDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LabMapperTest {
    private final LabMapper labMapper = new LabMapper();
    @Test
    void testToDTO() {
        Lab lab = new Lab();
        lab.setLabId("Plataformas");
        lab.setIsAvailable(new HashMap<>() {{put(LocalDateTime.now(), true);}});

        LabDTO labDTO = labMapper.toDTO(lab);

        assertNotNull(labDTO);
        assertEquals(lab.getLabId(), labDTO.getLabId());
        assertEquals(lab.getIsAvailable(), labDTO.getIsAvailable());
    }

    @Test
    void testToEntity() {
        LabDTO labDTO = new LabDTO();
        labDTO.setLabId("Plataformas");
        labDTO.setIsAvailable(new HashMap<>() {{put(LocalDateTime.now(), true);}});

        Lab lab = labMapper.toEntity(labDTO);

        assertNotNull(lab);
        assertEquals(labDTO.getLabId(), lab.getLabId());
        assertEquals(labDTO.getIsAvailable(), lab.getIsAvailable());
    }

    @Test
    void testToDTOWithNull() {
        assertNull(labMapper.toDTO(null));
    }

    @Test
    void testToEntityWithNull() {
        assertNull(labMapper.toEntity(null));
    }
}
