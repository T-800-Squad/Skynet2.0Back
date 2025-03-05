package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Lab;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface LabRepository extends MongoRepository<Lab, String> {

    boolean[][] checkAvailability();

    String[] checkAvailabilityLabExactMoment(int day, int timeLine);

    Lab getLab(String labId);

    void setLab(String labId, int day, int timeLine);
}
