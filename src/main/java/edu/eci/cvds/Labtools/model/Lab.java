package edu.eci.cvds.Labtools.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;


@Document(collection = "laboratories")
public class Lab {
    @Id
    private String labId;
    private String name;
    private HashMap<LocalDateTime, Boolean> isAvailable = new HashMap<>();

    public String getLabId() {
        return labId;
    }
    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<LocalDateTime, Boolean> getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(LocalDateTime localDateTime) {
        if(isAvailable.containsKey(localDateTime)) {
            throw new IllegalArgumentException("this lab already has a booking on this date");
        }
        isAvailable.put(localDateTime, true);
    }
}
