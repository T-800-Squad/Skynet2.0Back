package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Lab;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoLabRepository extends LabRepository {

    default boolean[][] checkAvailability() {
        return null;
    }

    default String[] checkAvailabilityLabExactMoment(int day, int timeLine) {
        return null;
    }

    default Lab getLab(String labId) {
        return null;
    }

    default void setLab(String labId, int day, int timeLine) {

    }
}
