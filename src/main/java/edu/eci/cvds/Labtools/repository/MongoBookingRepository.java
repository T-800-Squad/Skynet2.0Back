package edu.eci.cvds.Labtools.repository;


import edu.eci.cvds.Labtools.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReadPreference;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MongoBookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByUserId(String userId);
}
