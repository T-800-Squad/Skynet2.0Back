package edu.eci.cvds.Labtools.model;

public class Lab {

    private String labId;
    private boolean[][] isAvailable;

    public Lab(String labId) {
        this.labId = labId;
        this.isAvailable = new boolean[8][6];
    }
    public String getLabId() {
        return labId;
    }
    public boolean[][] getIsAvailable() {
        return isAvailable;
    }
}
