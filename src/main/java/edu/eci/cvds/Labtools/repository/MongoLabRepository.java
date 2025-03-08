package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MongoLabRepository extends MongoRepository<Lab, String> {

    Optional<Lab> findLabByLabId(String labId);
}
