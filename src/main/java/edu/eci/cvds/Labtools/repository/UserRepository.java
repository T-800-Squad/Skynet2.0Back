package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.model.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

    boolean userAuthentication(String email, String password);

    BookingDTO[] queryUserBooking(String userId);

    User getUser(String userId);
    void setUser(String userId, Booking booking);
}
