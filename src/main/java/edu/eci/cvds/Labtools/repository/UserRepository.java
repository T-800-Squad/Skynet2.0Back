package edu.eci.cvds.Labtools.repository;

import edu.eci.cvds.Labtools.model.User;

public interface UserRepository {

    String userAuthentication(String email, String password);

    void deleteBookingInUser(String bookingId, String userId);

    void queryUserBooking(String userId);

    User getUser(String userId);
}
