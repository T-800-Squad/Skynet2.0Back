package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Lab;

public interface LabRepository {

    boolean[][] checkAvailability();

    String[] checkAvailabilityLabExactMoment(int day, int timeLine);

    Lab getLab(String labId);

    void setLab(String labId, int day, int timeLine);
}
