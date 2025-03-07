package edu.eci.cvds.Labtools.model;

import java.time.LocalDateTime;
import java.util.HashMap;

public class LabDTO {
    private String labId;
    private HashMap<LocalDateTime,Boolean> isAvailable;
}
