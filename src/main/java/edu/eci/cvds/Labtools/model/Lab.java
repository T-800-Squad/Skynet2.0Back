package edu.eci.cvds.Labtools.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "laboratories")
public class Lab {

    private String labId;
    private boolean[][] isAvailable;
}
