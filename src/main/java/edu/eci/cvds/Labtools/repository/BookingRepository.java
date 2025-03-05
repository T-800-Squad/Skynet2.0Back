package edu.eci.cvds.Labtools.repository;


import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.model.Lab;
import edu.eci.cvds.Labtools.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookingRepository extends MongoRepository<Booking, String> {
    String createBooking(Booking booking);
    String deleteBooking(String bookingId);
}
