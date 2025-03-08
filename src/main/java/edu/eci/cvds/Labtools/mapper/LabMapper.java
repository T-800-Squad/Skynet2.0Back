package edu.eci.cvds.Labtools.mapper;

import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.LabDTO;

public class LabMapper implements GenericMapper<Lab, LabDTO> {
    @Override
    public LabDTO toDTO(Lab entity) {
        if (entity == null) {
            return null;
        }
        LabDTO dto = new LabDTO();
        dto.setLabId(entity.getLabId());
        dto.setIsAvailable(entity.getIsAvailable());
        return dto;
    }

    @Override
    public Lab toEntity(LabDTO labDTO){
        if (labDTO == null) {
            return null;
        }
        Lab lab = new Lab();
        lab.setLabId(labDTO.getLabId());
        lab.setIsAvailable(labDTO.getIsAvailable());
        return lab;
    }
}
