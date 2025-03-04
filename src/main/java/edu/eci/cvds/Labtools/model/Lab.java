package edu.eci.cvds.Labtools.model;

import lombok.Data;

@Data
public class Lab {

    private String labId;
    private boolean[][] isAvailable;
}
