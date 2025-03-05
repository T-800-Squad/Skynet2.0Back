package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.Booking;
import edu.eci.cvds.Labtools.model.BookingDTO;
import edu.eci.cvds.Labtools.model.User;
import edu.eci.cvds.Labtools.model.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public class BasicUserRepository implements UserRepository {

    public boolean userAuthentication(UserDTO userDTO) {
        return false;
    }

    public void deleteBookingInUser(String bookingId, String userId){

    }

    public BookingDTO[] queryUserBooking(String userId){
        return null;
    }

    public User getUser(String userId){
        return null;
    }

    public void setUser(String userId, Booking booking){

    }
}
