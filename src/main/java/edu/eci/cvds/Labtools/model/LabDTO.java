package edu.eci.cvds.Labtools.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
@Data
public class LabDTO {
    private String labId;
    private HashMap<LocalDateTime,Boolean> isAvailable;
}
