package edu.eci.cvds.Labtools.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@Document(collection = "laboratories")
public class Lab {
    @Id
    private String labId;
    private HashMap<LocalDateTime, Boolean> isAvailable;
}
