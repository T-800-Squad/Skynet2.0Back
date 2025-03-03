package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Lab;
import org.springframework.stereotype.Repository;

@Repository
public class BasicLabRepository implements LabRepository {

    public boolean[][] checkAvailability() {
        return null;
    }

    public String[] checkAvailabilityLabExactMoment(int day, int timeLine) {
        return null;
    }

    public Lab getLab(String labId) {
        return null;
    }

    public void setLab(String labId, int day, int timeLine) {

    }
}
