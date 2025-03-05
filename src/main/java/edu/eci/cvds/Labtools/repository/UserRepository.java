package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.model.UserDTO;


public interface UserRepository {

    boolean userAuthentication(UserDTO userDTO);

    void deleteBookingInUser(String bookingId, String userId);

    BookingDTO[] queryUserBooking(String userId);

    User getUser(String userId);
    void setUser(String userId, Booking booking);
}
